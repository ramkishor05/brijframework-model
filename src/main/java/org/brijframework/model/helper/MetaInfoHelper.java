package org.brijframework.model.helper;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import org.brijframework.container.Container;
import org.brijframework.model.info.OwnerModelInfo;
import org.brijframework.model.info.ConstModelInfo;
import org.brijframework.model.info.PptModelInfoGroup;
import org.brijframework.model.info.PptModelInfo;
import org.brijframework.model.info.RelMetaInfo;
import org.brijframework.model.info.impl.ClassMetaInfoImpl;
import org.brijframework.model.info.impl.ConstMetaInfoImpl;
import org.brijframework.model.info.impl.FieldModelInfoGroupImpl;
import org.brijframework.model.info.impl.FieldMetaInfoImpl;
import org.brijframework.model.info.impl.ReferMetaImpl;
import org.brijframework.model.setup.ClassMetaSetup;
import org.brijframework.model.setup.ConstMetaSetup;
import org.brijframework.model.setup.FieldMetaSetup;
import org.brijframework.model.setup.RelMetaSetup;
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
	
	public static OwnerModelInfo getModelInfo(Container container, Class<?> targetClass) {
		String id=targetClass.getSimpleName();
		String name=targetClass.getSimpleName();
		ClassMetaInfoImpl owner=new ClassMetaInfoImpl(targetClass,id,name);
		owner.setAccess(Access.PUBLIC);
		owner.setConstructor(getConsMetaInfo(owner));
		for (Field field : FieldUtil.getAllField(targetClass, Access.PRIVATE)) {
			owner.getProperties().put(field.getName(), buldPropertyGroup(owner, field.getName()));
		}
		return owner;
	}

	public static OwnerModelInfo getModelInfo(Container container, Class<?> _class, ClassMetaSetup metaSetup) {
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
	
	public static PptModelInfoGroup buldPropertyGroup(ClassMetaInfoImpl owner,String key, Property setup ) {
		FieldModelInfoGroupImpl propertyGroup=new  FieldModelInfoGroupImpl(owner);
		AccessibleObject field=MetaAccessorUtil.findFieldMeta(owner.getTarget(),key, Access.PRIVATE);
		if(field!=null) {
			PptModelInfo fieldMeta=buildFieldMeta(owner, field, setup);
			propertyGroup.setFieldMeta(fieldMeta);
		}
		AccessibleObject getter=MetaAccessorUtil.findGetterMeta(owner.getTarget(),key, Access.PRIVATE);
		if(getter!=null) {
			PptModelInfo getterMeta=buildFieldMeta(owner, getter, setup);
			propertyGroup.setGetterMeta(getterMeta);
		}
		AccessibleObject setter=MetaAccessorUtil.findSetterMeta(owner.getTarget(),key, Access.PRIVATE);
		if(setter!=null) {
			PptModelInfo setterMeta=buildFieldMeta(owner, setter, setup);
			propertyGroup.setSetterMeta(setterMeta);
		}
		return propertyGroup;
	}
	
	public static PptModelInfoGroup buldPropertyGroup(ClassMetaInfoImpl owner,String key, Relation setup ) {
		FieldModelInfoGroupImpl propertyGroup=new  FieldModelInfoGroupImpl(owner);
		AccessibleObject field=MetaAccessorUtil.findFieldMeta(owner.getTarget(),key, Access.PRIVATE);
		if(field!=null) {
			PptModelInfo fieldMeta=buildFieldMeta(owner, field, setup);
			propertyGroup.setFieldMeta(fieldMeta);
		}
		AccessibleObject getter=MetaAccessorUtil.findGetterMeta(owner.getTarget(),key, Access.PRIVATE);
		if(getter!=null) {
			PptModelInfo getterMeta=buildFieldMeta(owner, getter, setup);
			propertyGroup.setGetterMeta(getterMeta);
		}
		AccessibleObject setter=MetaAccessorUtil.findSetterMeta(owner.getTarget(),key, Access.PRIVATE);
		if(setter!=null) {
			PptModelInfo setterMeta=buildFieldMeta(owner, setter, setup);
			propertyGroup.setSetterMeta(setterMeta);
		}
		return propertyGroup;
	}
	
	public static PptModelInfoGroup buldPropertyGroup(ClassMetaInfoImpl owner,String key, FieldMetaSetup setup ) {
		FieldModelInfoGroupImpl propertyGroup=new  FieldModelInfoGroupImpl(owner);
		AccessibleObject field=MetaAccessorUtil.findFieldMeta(owner.getTarget(),key, Access.PRIVATE);
		if(field!=null) {
			PptModelInfo fieldMeta=buildFieldMeta(owner, field, setup);
			propertyGroup.setFieldMeta(fieldMeta);
		}
		AccessibleObject getter=MetaAccessorUtil.findGetterMeta(owner.getTarget(),key, Access.PRIVATE);
		if(getter!=null) {
			PptModelInfo getterMeta=buildFieldMeta(owner, getter, setup);
			propertyGroup.setGetterMeta(getterMeta);
		}
		AccessibleObject setter=MetaAccessorUtil.findSetterMeta(owner.getTarget(),key, Access.PRIVATE);
		if(setter!=null) {
			PptModelInfo setterMeta=buildFieldMeta(owner, setter, setup);
			propertyGroup.setSetterMeta(setterMeta);
		}
		return propertyGroup;
	}
	
	public static PptModelInfoGroup buldPropertyGroup(ClassMetaInfoImpl owner,String key) {
		FieldModelInfoGroupImpl propertyGroup=new  FieldModelInfoGroupImpl(owner);
		propertyGroup.setId(key);
		propertyGroup.setName(key);
		AccessibleObject field=MetaAccessorUtil.findFieldMeta(owner.getTarget(),key, Access.PRIVATE);
		if(field!=null) {
			PptModelInfo fieldMeta=buildFieldMeta(owner, field);
			propertyGroup.setFieldMeta(fieldMeta);
		}
		AccessibleObject getter=MetaAccessorUtil.findGetterMeta(owner.getTarget(),key, Access.PRIVATE);
		if(getter!=null) {
			PptModelInfo getterMeta=buildFieldMeta(owner, getter);
			propertyGroup.setGetterMeta(getterMeta);
		}
		AccessibleObject setter=MetaAccessorUtil.findSetterMeta(owner.getTarget(),key, Access.PRIVATE);
		if(setter!=null) {
			PptModelInfo setterMeta=buildFieldMeta(owner, setter);
			propertyGroup.setSetterMeta(setterMeta);
		}
		return propertyGroup;
	}

	public static OwnerModelInfo getModelInfo(Container container,Class<?> target, Model metaSetup) {
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

	private static ConstModelInfo getConsMetaInfo(ClassMetaInfoImpl owner, ConstMetaSetup constructor) {
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
	
	public static PptModelInfo buildFieldMeta(OwnerModelInfo owner, AccessibleObject target) {
		if (target instanceof Field) {
			return buildFieldMeta(owner, (Field) target);
		} else {
			return buildFieldMeta(owner, (Method) target);
		}
	}

	public static PptModelInfo buildFieldMeta(OwnerModelInfo owner,Method target) {
		FieldMetaInfoImpl metaInfo = new FieldMetaInfoImpl(owner, target);
		metaInfo.setId(target.getName());
		metaInfo.setName(target.getName());
		metaInfo.setAccess(Access.PRIVATE);
		metaInfo.setRequired(false);
		metaInfo.setType(CastingUtil.getTargetClass(target, target.getReturnType()));
		return metaInfo;
	}

	public static PptModelInfo buildFieldMeta(OwnerModelInfo owner, Field target) {
		ReferMetaImpl metaInfo = new ReferMetaImpl(owner, target);
		metaInfo.setId(target.getName());
		metaInfo.setName(target.getName());
		metaInfo.setAccess(Access.valueOf(target.getModifiers()));
		metaInfo.setRequired(false);
		metaInfo.setType(CastingUtil.getTargetClass(target, target.getType()));
		return metaInfo;
	}

	public static PptModelInfo buildFieldMeta(OwnerModelInfo owner, AccessibleObject target, Property property) {
		if (target instanceof Field) {
			return buildFieldMeta(owner, (Field) target, property);
		} else {
			return buildFieldMeta(owner, (Method) target, property);
		}
	}
	
	public static PptModelInfo buildFieldMeta(OwnerModelInfo owner, Field target,Property property) {
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
	
	public static PptModelInfo buildFieldMeta(OwnerModelInfo owner, Method target,Property property) {
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

	public static PptModelInfo buildFieldMeta(OwnerModelInfo owner, AccessibleObject target, FieldMetaSetup metaSetup) {
		if (target instanceof Field) {
			return buildFieldMeta(owner, (Field) target, metaSetup);
		} else {
			return buildFieldMeta(owner, (Method) target, metaSetup);
		}
	}
	
	public static PptModelInfo buildFieldMeta(OwnerModelInfo owner, Method target,FieldMetaSetup property) {
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
	
	public static PptModelInfo buildFieldMeta(OwnerModelInfo owner, Field target,FieldMetaSetup property) {
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
	
	public static RelMetaInfo buildFieldMeta(OwnerModelInfo owner, AccessibleObject target,RelMetaSetup metaSetup) {
		if (target instanceof Field) {
			return buildFieldMeta(owner, (Field) target, metaSetup);
		} else {
			return buildFieldMeta(owner, (Method) target, metaSetup);
		}
	}
	
	public static RelMetaInfo buildFieldMeta(OwnerModelInfo owner, Field target,RelMetaSetup property) {
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
	

	public static RelMetaInfo buildFieldMeta(OwnerModelInfo owner, Method target,RelMetaSetup property) {
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
	
	
	public static RelMetaInfo buildFieldMeta(OwnerModelInfo owner, AccessibleObject target, Relation property) {
		if (target instanceof Field) {
			return buildFieldMeta(owner, (Field) target, property);
		} else {
			return buildFieldMeta(owner, (Method) target, property);
		}
	}
	
	public static RelMetaInfo buildFieldMeta(OwnerModelInfo owner, Field target,Relation property) {
		ReferMetaImpl metaInfo = new ReferMetaImpl(owner, target);
		String id = property.id() == null || property.id().equals(Constants.DEFAULT)? target.getName() : property.id();
		metaInfo.setId(id);
		metaInfo.setName(target.getName());
		metaInfo.setAccess(property.access());
		metaInfo.setRequired(property.required());
		metaInfo.setType(property.type());
		return metaInfo;
	}
	
	public static ReferMetaImpl buildFieldMeta(OwnerModelInfo owner, Method target,Relation property) {
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
