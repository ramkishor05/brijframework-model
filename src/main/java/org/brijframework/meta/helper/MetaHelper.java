package org.brijframework.meta.helper;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import org.brijframework.container.Container;
import org.brijframework.meta.impl.ModelConsMeta;
import org.brijframework.meta.impl.ModelMeta;
import org.brijframework.meta.impl.PropertyKey;
import org.brijframework.meta.impl.PropertyMeta;
import org.brijframework.meta.impl.RelPtpMeta;
import org.brijframework.meta.reflect.ClassMeta;
import org.brijframework.meta.reflect.ConstMeta;
import org.brijframework.meta.setup.ClassMetaSetup;
import org.brijframework.meta.setup.FieldMetaSetup;
import org.brijframework.meta.setup.RelationMetaSetup;
import org.brijframework.support.model.Construct;
import org.brijframework.support.model.Model;
import org.brijframework.support.model.Property;
import org.brijframework.support.model.Relation;
import org.brijframework.util.accessor.MetaAccessorUtil;
import org.brijframework.util.casting.CastingUtil;
import org.brijframework.util.reflect.ClassUtil;
import org.brijframework.util.reflect.ConstructUtil;
import org.brijframework.util.support.Access;
import org.brijframework.util.support.Constants;

public class MetaHelper {

	public static ClassMeta getModelInfo(Container container, Class<?> _class, ClassMetaSetup metaSetup) {
		Objects.requireNonNull(metaSetup, "MetaSetup should be required");
		final Class<?>targetClass =_class==null?ClassUtil.getClass(metaSetup.getTarget()):_class;
		Objects.requireNonNull(targetClass, "Target should be required");
		String id=metaSetup.getId()==null|| metaSetup.getId().equals(Constants.DEFAULT)?targetClass.getSimpleName():metaSetup.getId();
		String name=metaSetup.getName()==null|| metaSetup.getName().equals(Constants.DEFAULT)?targetClass.getSimpleName():metaSetup.getName();
		ModelMeta owner=new ModelMeta(targetClass,id,name);
		owner.setAccess(metaSetup.getAccess()!=null?Access.valueOf(metaSetup.getAccess()):Access.DEFAULT);
		if(metaSetup.getConstructor()!=null) {
			owner.setConstructor(getConsMetaInfo(owner, metaSetup.getConstructor()));
		}else {
			owner.setConstructor(getConsMetaInfo(owner));
		}
		if(metaSetup.getProperties()!=null) {
			metaSetup.getProperties().forEach((key,setup)->{
				AccessibleObject target=MetaAccessorUtil.getPropertyMeta(targetClass,key,Access.PRIVATE);
				registerPropertyMeta(owner, target, setup);
			});
		}
		return owner;
	}
	

	public static ClassMeta getModelInfo(Container container,Class<?> target, Model metaSetup) {
		Objects.requireNonNull(target, "Target should be required");
		Objects.requireNonNull(metaSetup, "Meta should be required");
		String id=metaSetup.id()==null|| metaSetup.id().equals(Constants.DEFAULT)?target.getSimpleName():metaSetup.id();
		String name=target.getSimpleName();
		ModelMeta owner=new ModelMeta(target,id,name);
		owner.setAccess(metaSetup.access()!=null?metaSetup.access():Access.DEFAULT);
		if(metaSetup.constructor()!=null) {
			owner.setConstructor(getConsMetaInfo(owner, metaSetup.constructor()));
		}else {
			owner.setConstructor(getConsMetaInfo(owner));
		}
		List<AccessibleObject> properties= MetaAccessorUtil.getPropertiesMeta(target,Access.PRIVATE);
		for(AccessibleObject targetField:properties) {
			if(targetField.isAnnotationPresent(Property.class)) {
				registerPropertyMeta(owner, targetField, (Property)targetField.getAnnotation(Property.class));
			}else if(targetField.isAnnotationPresent(Relation.class)) {
				registerPropertyMeta(owner, targetField, (Relation)targetField.getAnnotation(Relation.class));
			}else {
				registerPropertyMeta(owner, targetField);
			}
		}
		return owner;
	}
	
	public static ModelConsMeta getConsMetaInfo(ModelMeta owner) {
		ModelConsMeta consMetaInfo=new ModelConsMeta(owner);
		consMetaInfo.setAccess(owner.getAccess());
		consMetaInfo.setOwner(owner);
		consMetaInfo.setTarget(ConstructUtil.getConstructor(owner.getTarget(), owner.getAccess(), consMetaInfo.getArguments()));
		return consMetaInfo;
	}
	
	public static ModelConsMeta getConsMetaInfo(ModelMeta owner,Construct constructor) {
		ModelConsMeta consMetaInfo=new ModelConsMeta(owner);
		consMetaInfo.setAccess(constructor.access());
		consMetaInfo.setOwner(owner);
		if(constructor.params()!=null) {
			Type[] arguments=new Type[constructor.params().length];
			Object[] values=new Object[constructor.params().length];
			AtomicInteger index=new AtomicInteger(0);
			Arrays.asList(constructor.params()).stream().sorted((param1,param2)->{
				if(param1.index()>param2.index()) {
					return 1;
				}
				if(param1.index()<param2.index()) {
					return -1;
				}
				return 0;
			}).forEach(param->{
				arguments[index.get()]=param.type();
				values[index.getAndIncrement()]=CastingUtil.castObject(param.value(), param.type());
			});
			consMetaInfo.setArguments(arguments);
			consMetaInfo.setValues(values);
		}
		consMetaInfo.setTarget(ConstructUtil.getConstructor(owner.getTarget(), constructor.access(), consMetaInfo.getArguments()));
		return consMetaInfo;
	}

	private static ConstMeta getConsMetaInfo(ModelMeta owner, ConstMeta constructor) {
		ModelConsMeta consMetaInfo=new ModelConsMeta(owner);
		consMetaInfo.setAccess(constructor.getAccess());
		consMetaInfo.setOwner(owner);
		if(constructor.getParams()!=null) {
			Type[] arguments=new Type[constructor.getParams().size()];
			Object[] values=new Object[constructor.getParams().size()];
			AtomicInteger index=new AtomicInteger(0);
			constructor.getParams().stream().sorted((param1,param2)->{
				if(param1.getIndex()>param2.getIndex()) {
					return 1;
				}
				if(param1.getIndex()<param2.getIndex()) {
					return -1;
				}
				return 0;
			}).forEach(param->{
				arguments[index.get()]=param.getType();
				values[index.getAndIncrement()]=CastingUtil.castObject(param.getValue(), param.getType());
			});
			consMetaInfo.setArguments(arguments);
			consMetaInfo.setValues(values);
		}
		consMetaInfo.setTarget(ConstructUtil.getConstructor(owner.getTarget(), constructor.getAccess(), consMetaInfo.getArguments()));
		return consMetaInfo;
	}
	
	public static void registerPropertyMeta(ClassMeta owner, AccessibleObject target) {
		if (target instanceof Field) {
			registerPropertyMeta(owner, (Field) target);
		} else {
			registerPropertyMeta(owner, (Method) target);
		}
	}

	public static void registerPropertyMeta(ClassMeta owner, Method target) {
		String id = target.getName();
		RelPtpMeta metaInfo = new RelPtpMeta(owner, target);
		metaInfo.setId(id);
		String name=target.getName();
		metaInfo.setName(name);
		metaInfo.setAccess(Access.PRIVATE);
		metaInfo.setRequired(false);
		metaInfo.setType(CastingUtil.getTargetClass(target, target.getReturnType()));
		owner.getProperties().put(metaInfo.getId(), metaInfo);
	}

	public static void registerPropertyMeta(ClassMeta owner, Field target) {
		String id = target.getName();
		RelPtpMeta metaInfo = new RelPtpMeta(owner, target);
		metaInfo.setId(id);
		String name=target.getName();
		metaInfo.setName(name);
		metaInfo.setAccess(Access.PRIVATE);
		metaInfo.setRequired(false);
		metaInfo.setType(CastingUtil.getTargetClass(target, target.getType()));
		owner.getProperties().put(metaInfo.getId(), metaInfo);
	}
	
	public static void registerPropertyMeta(ClassMeta owner, AccessibleObject target,
			Relation property) {
		if (target instanceof Field) {
			registerPropertyMeta(owner, (Field) target, property);
		} else {
			registerPropertyMeta(owner, (Method) target, property);
		}
	}

	public static void registerPropertyMeta(ClassMeta owner, AccessibleObject target,
			Property property) {
		if (target instanceof Field) {
			registerPropertyMeta(owner, (Field) target, property);
		} else {
			registerPropertyMeta(owner, (Method) target, property);
		}
	}
	
	public static void registerPropertyMeta(ClassMeta owner, Method target,Relation property) {
		String id = property.id() == null || property.id().equals(Constants.DEFAULT)? target.getName() : property.id();
		RelPtpMeta metaInfo = new RelPtpMeta(owner, target);
		metaInfo.setId(id);
		String name=target.getName();
		metaInfo.setName(name);
		metaInfo.setAccess(property.access());
		metaInfo.setValue(property.value());
		metaInfo.setRequired(property.required());
		metaInfo.setType(property.type());
		owner.getProperties().put(metaInfo.getId(), metaInfo);
	}
	
	public static void registerPropertyMeta(ClassMeta owner, Field target,Relation property) {
		String id = property.id() == null || property.id().equals(Constants.DEFAULT)? target.getName() : property.id();
		RelPtpMeta metaInfo = new RelPtpMeta(owner, target);
		metaInfo.setId(id);
		metaInfo.setName(target.getName());
		metaInfo.setAccess(property.access());
		metaInfo.setValue(property.value());
		metaInfo.setRequired(property.required());
		metaInfo.setMappedBy(property.mappedBy());
		metaInfo.setType(property.type());
		owner.getProperties().put(metaInfo.getId(), metaInfo);
	}
	
	
	public static void registerPropertyMeta(ClassMeta owner, Field target,Property property) {
		String id = property.id() == null || property.id().equals(Constants.DEFAULT)? target.getName() : property.id();
		PropertyMeta metaInfo = new PropertyMeta(owner, target);
		metaInfo.setId(id);
		metaInfo.setName(target.getName());
		metaInfo.setAccess(property.access());
		metaInfo.setValue(property.value());
		metaInfo.setRequired(property.required());
		metaInfo.setType(property.type());
		owner.getProperties().put(metaInfo.getId(), metaInfo);
	}
	
	public static void registerPropertyMeta(ClassMeta owner, Method target,Property property) {
		String id = property.id() == null || property.id().equals(Constants.DEFAULT)? target.getName() : property.id();
		PropertyMeta metaInfo = new PropertyMeta(owner, target);
		metaInfo.setId(id);
		String name=target.getName();
		metaInfo.setName(name);
		metaInfo.setAccess(property.access());
		metaInfo.setValue(property.value());
		metaInfo.setRequired(property.required());
		metaInfo.setType(property.type());
		owner.getProperties().put(metaInfo.getId(), metaInfo);
	}
	
	public static PropertyMeta getPropertyMetaInfo(ClassMeta owner, Method target,FieldMetaSetup property) {
		String id = property.getId() == null || property.getId().equals(Constants.DEFAULT)? target.getName() : property.getId();
		PropertyMeta metaInfo = new PropertyMeta(owner, target);
		metaInfo.setId(id);
		String name=target.getName();
		metaInfo.setName(name);
		metaInfo.setAccess(Access.valueOf(property.getAccess()));
		metaInfo.setValue(property.getValue());
		metaInfo.setRequired(property.isRequired());
		if(property.getType()!=null && !property.getType().isEmpty())
		metaInfo.setType(ClassUtil.getClass(property.getType()));
		PropertyKey keyInfo=new PropertyKey(metaInfo);
		keyInfo.init();
		return metaInfo;
	}
	
	public static PropertyMeta getPropertyMetaInfo(ClassMeta owner, Field target,FieldMetaSetup property) {
		String id = property.getId() == null || property.getId().equals(Constants.DEFAULT)? target.getName() : property.getId();
		PropertyMeta metaInfo = new PropertyMeta(owner, target);
		metaInfo.setId(id);
		String name=target.getName();
		metaInfo.setName(name);
		metaInfo.setAccess(Access.valueOf(property.getAccess()));
		metaInfo.setRequired(property.isRequired());
		if(property.getType()!=null && !property.getType().isEmpty())
			metaInfo.setType(ClassUtil.getClass(property.getType()));
		PropertyKey keyInfo=new PropertyKey(metaInfo);
		keyInfo.init();
		return metaInfo;
	}
	
	public static RelPtpMeta getPropertyMetaInfo(ClassMeta owner, Field target,RelationMetaSetup property) {
		String id = property.getId() == null || property.getId().equals(Constants.DEFAULT)? target.getName() : property.getId();
		RelPtpMeta metaInfo = new RelPtpMeta(owner, target);
		metaInfo.setId(id);
		String name=target.getName();
		metaInfo.setName(name);
		metaInfo.setAccess(Access.valueOf(property.getAccess()));
		metaInfo.setValue(property.getValue());
		metaInfo.setRequired(property.isRequired());
		if(property.getType()!=null && !property.getType().isEmpty())
			metaInfo.setType(ClassUtil.getClass(property.getType()));
		PropertyKey keyInfo=new PropertyKey(metaInfo);
		keyInfo.init();
		return metaInfo;
	}
	
	public static RelPtpMeta getPropertyMetaInfo(ClassMeta owner, Method target,RelationMetaSetup property) {
		String id = property.getId() == null || property.getId().equals(Constants.DEFAULT)? target.getName() : property.getId();
		RelPtpMeta metaInfo = new RelPtpMeta(owner, target);
		metaInfo.setId(id);
		String name=target.getName();
		metaInfo.setName(name);
		metaInfo.setAccess(Access.valueOf(property.getAccess()));
		metaInfo.setValue(property.getValue());
		metaInfo.setRequired(property.isRequired());
		if(property.getType()!=null && !property.getType().isEmpty())
			metaInfo.setType(ClassUtil.getClass(property.getType()));
		PropertyKey keyInfo=new PropertyKey(metaInfo);
		keyInfo.init();
		return metaInfo;
	}

	public static PropertyMeta registerPropertyMeta(ClassMeta owner, AccessibleObject target, FieldMetaSetup metaSetup) {
		if (target instanceof Field) {
			return getPropertyMetaInfo(owner, (Field) target, metaSetup);
		} else {
			return getPropertyMetaInfo(owner, (Method) target, metaSetup);
		}
	}
	
	public static RelPtpMeta getPropertyMetaInfo(ClassMeta owner, AccessibleObject target,
			RelationMetaSetup metaSetup) {
		if (target instanceof Field) {
			return getPropertyMetaInfo(owner, (Field) target, metaSetup);
		} else {
			return getPropertyMetaInfo(owner, (Method) target, metaSetup);
		}
	}
}
