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
import org.brijframework.meta.impl.ClassMetaInfoImpl;
import org.brijframework.meta.impl.ConstMetaInfoImpl;
import org.brijframework.meta.impl.FieldGroupImpl;
import org.brijframework.meta.impl.FieldMetaInfoImpl;
import org.brijframework.meta.impl.ReferMetaImpl;
import org.brijframework.meta.info.ClassMetaInfo;
import org.brijframework.meta.info.ConstMetaInfo;
import org.brijframework.meta.info.FieldGroup;
import org.brijframework.meta.info.FieldMetaInfo;
import org.brijframework.meta.info.RelMetaInfo;
import org.brijframework.meta.setup.ClassMetaSetup;
import org.brijframework.meta.setup.ConstMetaSetup;
import org.brijframework.meta.setup.FieldMetaSetup;
import org.brijframework.meta.setup.RelMetaSetup;
import org.brijframework.support.model.Construct;
import org.brijframework.support.model.Model;
import org.brijframework.support.model.Property;
import org.brijframework.support.model.Relation;
import org.brijframework.util.accessor.MetaAccessorUtil;
import org.brijframework.util.casting.CastingUtil;
import org.brijframework.util.reflect.ClassUtil;
import org.brijframework.util.reflect.ConstructUtil;
import org.brijframework.util.reflect.FieldUtil;
import org.brijframework.util.support.Access;
import org.brijframework.util.support.Constants;

public class MetaInfoHelper {

	public static ClassMetaInfo getModelInfo(Container container, Class<?> _class, ClassMetaSetup metaSetup) {
		Objects.requireNonNull(metaSetup, "MetaSetup should be required");
		final Class<?>targetClass =_class==null?ClassUtil.getClass(metaSetup.getTarget()):_class;
		Objects.requireNonNull(targetClass, "Target should be required");
		String id=metaSetup.getId()==null|| metaSetup.getId().equals(Constants.DEFAULT)?targetClass.getSimpleName():metaSetup.getId();
		String name=metaSetup.getName()==null|| metaSetup.getName().equals(Constants.DEFAULT)?targetClass.getSimpleName():metaSetup.getName();
		ClassMetaInfoImpl owner=new ClassMetaInfoImpl(targetClass,id,name);
		owner.setAccess(metaSetup.getAccess()!=null?Access.valueOf(metaSetup.getAccess()):Access.DEFAULT);
		if(metaSetup.getConstructor()!=null) {
			owner.setConstructor(getConsMetaInfo(owner, metaSetup.getConstructor()));
		}else {
			owner.setConstructor(getConsMetaInfo(owner));
		}
		if(metaSetup.getProperties()!=null) {
			metaSetup.getProperties().forEach((key,setup)->{
				owner.getProperties().put(key, buldPropertyGroup(owner, key, setup));
			});
		}
		return owner;
	}
	
	public static FieldGroup buldPropertyGroup(ClassMetaInfoImpl owner,String key, Property setup ) {
		FieldGroupImpl propertyGroup=new  FieldGroupImpl(owner);
		AccessibleObject field=MetaAccessorUtil.getFieldMeta(owner.getTarget(),key, Access.PRIVATE);
		if(field!=null) {
			FieldMetaInfo fieldMeta=buildFieldMeta(owner, field, setup);
			propertyGroup.setFieldMeta(fieldMeta);
		}
		AccessibleObject getter=MetaAccessorUtil.getPropertyMeta(owner.getTarget(),key, Access.PRIVATE);
		if(getter!=null) {
			FieldMetaInfo getterMeta=buildFieldMeta(owner, getter, setup);
			propertyGroup.setGetterMeta(getterMeta);
		}
		AccessibleObject setter=MetaAccessorUtil.setPropertyMeta(owner.getTarget(),key, Access.PRIVATE);
		if(setter!=null) {
			FieldMetaInfo setterMeta=buildFieldMeta(owner, setter, setup);
			propertyGroup.setSetterMeta(setterMeta);
		}
		return propertyGroup;
	}
	
	public static FieldGroup buldPropertyGroup(ClassMetaInfoImpl owner,String key, Relation setup ) {
		FieldGroupImpl propertyGroup=new  FieldGroupImpl(owner);
		AccessibleObject field=MetaAccessorUtil.getFieldMeta(owner.getTarget(),key, Access.PRIVATE);
		if(field!=null) {
			FieldMetaInfo fieldMeta=buildFieldMeta(owner, field, setup);
			propertyGroup.setFieldMeta(fieldMeta);
		}
		AccessibleObject getter=MetaAccessorUtil.getPropertyMeta(owner.getTarget(),key, Access.PRIVATE);
		if(getter!=null) {
			FieldMetaInfo getterMeta=buildFieldMeta(owner, getter, setup);
			propertyGroup.setGetterMeta(getterMeta);
		}
		AccessibleObject setter=MetaAccessorUtil.setPropertyMeta(owner.getTarget(),key, Access.PRIVATE);
		if(setter!=null) {
			FieldMetaInfo setterMeta=buildFieldMeta(owner, setter, setup);
			propertyGroup.setSetterMeta(setterMeta);
		}
		return propertyGroup;
	}
	
	public static FieldGroup buldPropertyGroup(ClassMetaInfoImpl owner,String key, FieldMetaSetup setup ) {
		FieldGroupImpl propertyGroup=new  FieldGroupImpl(owner);
		AccessibleObject field=MetaAccessorUtil.getFieldMeta(owner.getTarget(),key, Access.PRIVATE);
		if(field!=null) {
			FieldMetaInfo fieldMeta=buildFieldMeta(owner, field, setup);
			propertyGroup.setFieldMeta(fieldMeta);
		}
		AccessibleObject getter=MetaAccessorUtil.getPropertyMeta(owner.getTarget(),key, Access.PRIVATE);
		if(getter!=null) {
			FieldMetaInfo getterMeta=buildFieldMeta(owner, getter, setup);
			propertyGroup.setGetterMeta(getterMeta);
		}
		AccessibleObject setter=MetaAccessorUtil.setPropertyMeta(owner.getTarget(),key, Access.PRIVATE);
		if(setter!=null) {
			FieldMetaInfo setterMeta=buildFieldMeta(owner, setter, setup);
			propertyGroup.setSetterMeta(setterMeta);
		}
		return propertyGroup;
	}
	
	public static FieldGroup buldPropertyGroup(ClassMetaInfoImpl owner,String key) {
		FieldGroupImpl propertyGroup=new  FieldGroupImpl(owner);
		propertyGroup.setId(key);
		propertyGroup.setName(key);
		AccessibleObject field=MetaAccessorUtil.getFieldMeta(owner.getTarget(),key, Access.PRIVATE);
		if(field!=null) {
			FieldMetaInfo fieldMeta=buildFieldMeta(owner, field);
			propertyGroup.setFieldMeta(fieldMeta);
		}
		AccessibleObject getter=MetaAccessorUtil.getPropertyMeta(owner.getTarget(),key, Access.PRIVATE);
		if(getter!=null) {
			FieldMetaInfo getterMeta=buildFieldMeta(owner, getter);
			propertyGroup.setGetterMeta(getterMeta);
		}
		AccessibleObject setter=MetaAccessorUtil.setPropertyMeta(owner.getTarget(),key, Access.PRIVATE);
		if(setter!=null) {
			FieldMetaInfo setterMeta=buildFieldMeta(owner, setter);
			propertyGroup.setSetterMeta(setterMeta);
		}
		return propertyGroup;
	}

	public static ClassMetaInfo getModelInfo(Container container,Class<?> target, Model metaSetup) {
		Objects.requireNonNull(target, "Target should be required");
		Objects.requireNonNull(metaSetup, "Meta should be required");
		String id=metaSetup.id()==null|| metaSetup.id().equals(Constants.DEFAULT)?target.getSimpleName():metaSetup.id();
		String name=target.getSimpleName();
		ClassMetaInfoImpl owner=new ClassMetaInfoImpl(target,id,name);
		owner.setAccess(metaSetup.access()!=null?metaSetup.access():Access.DEFAULT);
		if(metaSetup.constructor()!=null) {
			owner.setConstructor(getConsMetaInfo(owner, metaSetup.constructor()));
		}else {
			owner.setConstructor(getConsMetaInfo(owner));
		}
		List<Field> properties= FieldUtil.getAllField(target,Access.PRIVATE_NO_STATIC_FINAL);
		for(Field targetField:properties) {
			if(targetField.isAnnotationPresent(Property.class)) {
				owner.getProperties().put(targetField.getName(), buldPropertyGroup(owner, targetField.getName(), (Property) targetField.getAnnotation(Property.class)));
			}else if(targetField.isAnnotationPresent(Relation.class)) {
				owner.getProperties().put(targetField.getName(), buldPropertyGroup(owner, targetField.getName(), (Relation) targetField.getAnnotation(Relation.class)));
			}else {
				owner.getProperties().put(targetField.getName(), buldPropertyGroup(owner, targetField.getName()));
			}
		}
		return owner;
	}
	
	/*
	 * Constructor builder
	 */
	
	public static ConstMetaInfoImpl getConsMetaInfo(ClassMetaInfoImpl owner) {
		ConstMetaInfoImpl consMetaInfo=new ConstMetaInfoImpl(owner);
		consMetaInfo.setAccess(owner.getAccess());
		consMetaInfo.setOwner(owner);
		consMetaInfo.setTarget(ConstructUtil.getConstructor(owner.getTarget(), owner.getAccess(), consMetaInfo.getArguments()));
		return consMetaInfo;
	}
	
	public static ConstMetaInfoImpl getConsMetaInfo(ClassMetaInfoImpl owner,Construct constructor) {
		ConstMetaInfoImpl consMetaInfo=new ConstMetaInfoImpl(owner);
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

	private static ConstMetaInfo getConsMetaInfo(ClassMetaInfoImpl owner, ConstMetaSetup constructor) {
		ConstMetaInfoImpl consMetaInfo=new ConstMetaInfoImpl(owner);
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
	
	/*
	 * Default property builder
	 */
	
	public static FieldMetaInfo buildFieldMeta(ClassMetaInfo owner, AccessibleObject target) {
		if (target instanceof Field) {
			return buildFieldMeta(owner, (Field) target);
		} else {
			return buildFieldMeta(owner, (Method) target);
		}
	}

	public static FieldMetaInfo buildFieldMeta(ClassMetaInfo owner,Method target) {
		FieldMetaInfoImpl metaInfo = new FieldMetaInfoImpl(owner, target);
		metaInfo.setId(target.getName());
		metaInfo.setName(target.getName());
		metaInfo.setAccess(Access.PRIVATE);
		metaInfo.setRequired(false);
		metaInfo.setType(CastingUtil.getTargetClass(target, target.getReturnType()));
		return metaInfo;
	}

	public static FieldMetaInfo buildFieldMeta(ClassMetaInfo owner, Field target) {
		ReferMetaImpl metaInfo = new ReferMetaImpl(owner, target);
		metaInfo.setId(target.getName());
		metaInfo.setName(target.getName());
		metaInfo.setAccess(Access.valueOf(target.getModifiers()));
		metaInfo.setRequired(false);
		metaInfo.setType(CastingUtil.getTargetClass(target, target.getType()));
		return metaInfo;
	}

	public static FieldMetaInfo buildFieldMeta(ClassMetaInfo owner, AccessibleObject target, Property property) {
		if (target instanceof Field) {
			return buildFieldMeta(owner, (Field) target, property);
		} else {
			return buildFieldMeta(owner, (Method) target, property);
		}
	}
	
	public static FieldMetaInfo buildFieldMeta(ClassMetaInfo owner, Field target,Property property) {
		FieldMetaInfoImpl metaInfo = new FieldMetaInfoImpl(owner, target);
		String id = property.id() == null || property.id().equals(Constants.DEFAULT)? target.getName() : property.id();
		metaInfo.setId(id);
		metaInfo.setName(target.getName());
		metaInfo.setAccess(property.access());
		metaInfo.setValue(property.value());
		metaInfo.setRequired(property.required());
		metaInfo.setType(property.type());
		return metaInfo;
	}
	
	public static FieldMetaInfo buildFieldMeta(ClassMetaInfo owner, Method target,Property property) {
		FieldMetaInfoImpl metaInfo = new FieldMetaInfoImpl(owner, target);
		String id = property.id() == null || property.id().equals(Constants.DEFAULT)? target.getName() : property.id();
		metaInfo.setId(id);
		metaInfo.setName(target.getName());
		metaInfo.setAccess(property.access());
		metaInfo.setValue(property.value());
		metaInfo.setRequired(property.required());
		metaInfo.setType(property.type());
		return metaInfo;
	}

	public static FieldMetaInfo buildFieldMeta(ClassMetaInfo owner, AccessibleObject target, FieldMetaSetup metaSetup) {
		if (target instanceof Field) {
			return buildFieldMeta(owner, (Field) target, metaSetup);
		} else {
			return buildFieldMeta(owner, (Method) target, metaSetup);
		}
	}
	
	public static FieldMetaInfo buildFieldMeta(ClassMetaInfo owner, Method target,FieldMetaSetup property) {
		FieldMetaInfoImpl metaInfo = new FieldMetaInfoImpl(owner, target);
		String id = property.getId() == null || property.getId().equals(Constants.DEFAULT)? target.getName() : property.getId();
		metaInfo.setId(id);
		metaInfo.setName(target.getName());
		metaInfo.setAccess(Access.valueOf(property.getAccess()));
		metaInfo.setValue(property.getValue());
		metaInfo.setRequired(property.isRequired());
		if(property.getType()!=null && !property.getType().isEmpty())
		metaInfo.setType(ClassUtil.getClass(property.getType()));
		return metaInfo;
	}
	
	public static FieldMetaInfo buildFieldMeta(ClassMetaInfo owner, Field target,FieldMetaSetup property) {
		FieldMetaInfoImpl metaInfo = new FieldMetaInfoImpl(owner, target);
		String id = property.getId() == null || property.getId().equals(Constants.DEFAULT)? target.getName() : property.getId();
		metaInfo.setId(id);
		metaInfo.setName(target.getName());
		metaInfo.setAccess(Access.valueOf(property.getAccess()));
		metaInfo.setRequired(property.isRequired());
		if(property.getType()!=null && !property.getType().isEmpty()) {
			metaInfo.setType(ClassUtil.getClass(property.getType()));
		}
		return metaInfo;
	}
	
	/*
	 * Relation meta builder
	 */
	/**
	 * 
	 * @param owner
	 * @param target
	 * @param metaSetup
	 * @return
	 */
	
	public static RelMetaInfo buildFieldMeta(ClassMetaInfo owner, AccessibleObject target,RelMetaSetup metaSetup) {
		if (target instanceof Field) {
			return buildFieldMeta(owner, (Field) target, metaSetup);
		} else {
			return buildFieldMeta(owner, (Method) target, metaSetup);
		}
	}
	
	public static RelMetaInfo buildFieldMeta(ClassMetaInfo owner, Field target,RelMetaSetup property) {
		ReferMetaImpl metaInfo = new ReferMetaImpl(owner, target);
		String id = property.getId() == null || property.getId().equals(Constants.DEFAULT)? target.getName() : property.getId();
		metaInfo.setId(id);
		metaInfo.setName(target.getName());
		metaInfo.setAccess(Access.valueOf(property.getAccess()));
		metaInfo.setValue(property.getValue());
		metaInfo.setRequired(property.isRequired());
		if(property.getType()!=null && !property.getType().isEmpty()) {
			metaInfo.setType(ClassUtil.getClass(property.getType()));
		}
		return metaInfo;
	}
	

	public static RelMetaInfo buildFieldMeta(ClassMetaInfo owner, Method target,RelMetaSetup property) {
		String id = property.getId() == null || property.getId().equals(Constants.DEFAULT)? target.getName() : property.getId();
		ReferMetaImpl metaInfo = new ReferMetaImpl(owner, target);
		metaInfo.setId(id);
		metaInfo.setName(target.getName());
		metaInfo.setAccess(Access.valueOf(property.getAccess()));
		metaInfo.setValue(property.getValue());
		metaInfo.setRequired(property.isRequired());
		if(property.getType()!=null && !property.getType().isEmpty()) {
			metaInfo.setType(ClassUtil.getClass(property.getType()));
		}
		return metaInfo;
	}
	
	
	public static RelMetaInfo buildFieldMeta(ClassMetaInfo owner, AccessibleObject target, Relation property) {
		if (target instanceof Field) {
			return buildFieldMeta(owner, (Field) target, property);
		} else {
			return buildFieldMeta(owner, (Method) target, property);
		}
	}
	
	public static RelMetaInfo buildFieldMeta(ClassMetaInfo owner, Field target,Relation property) {
		ReferMetaImpl metaInfo = new ReferMetaImpl(owner, target);
		String id = property.id() == null || property.id().equals(Constants.DEFAULT)? target.getName() : property.id();
		metaInfo.setId(id);
		metaInfo.setName(target.getName());
		metaInfo.setAccess(property.access());
		metaInfo.setRequired(property.required());
		metaInfo.setType(property.type());
		return metaInfo;
	}
	
	public static ReferMetaImpl buildFieldMeta(ClassMetaInfo owner, Method target,Relation property) {
		ReferMetaImpl metaInfo = new ReferMetaImpl(owner, target);
		String id = property.id() == null || property.id().equals(Constants.DEFAULT)? target.getName() : property.id();
		metaInfo.setId(id);
		metaInfo.setName(target.getName());
		metaInfo.setAccess(property.access());
		metaInfo.setRequired(property.required());
		metaInfo.setType(property.type());
		return metaInfo;
	}
	
	
}
