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
import org.brijframework.model.diffination.ModelConstructorDiffination;
import org.brijframework.model.diffination.ModelPropertyDiffination;
import org.brijframework.model.diffination.ModelPropertyDiffinationGroup;
import org.brijframework.model.diffination.ModelRelationDiffination;
import org.brijframework.model.diffination.ModelTypeDiffination;
import org.brijframework.model.diffination.impl.ModelConstructorDiffinationImpl;
import org.brijframework.model.diffination.impl.ModelPropertyDiffinationGroupImpl;
import org.brijframework.model.diffination.impl.ModelPropertyDiffinationImpl;
import org.brijframework.model.diffination.impl.ModelRelationDiffinationImpl;
import org.brijframework.model.diffination.impl.ModelTypeDiffinationImpl;
import org.brijframework.model.resource.ConstructorModelResource;
import org.brijframework.model.resource.PropertyModelResource;
import org.brijframework.model.resource.RelationPropertyModelResource;
import org.brijframework.model.resource.TypeModelResource;
import org.brijframework.support.model.Construct;
import org.brijframework.support.model.Model;
import org.brijframework.support.model.properties.Property;
import org.brijframework.support.model.properties.Relation;
import org.brijframework.util.accessor.MetaAccessorUtil;
import org.brijframework.util.casting.CastingUtil;
import org.brijframework.util.reflect.ClassUtil;
import org.brijframework.util.reflect.ConstructUtil;
import org.brijframework.util.reflect.FieldUtil;
import org.brijframework.util.support.Access;
import org.brijframework.util.support.Constants;

public class MetaDataHelper {
	
	public static ModelTypeDiffination getModelInfo(Container container, Class<?> targetClass) {
		String id=targetClass.getSimpleName();
		String name=targetClass.getSimpleName();
		ModelTypeDiffinationImpl owner=new ModelTypeDiffinationImpl(targetClass,id,name);
		owner.setAccess(Access.PUBLIC);
		owner.setConstructor(getConsMetaInfo(owner));
		for (Field field : FieldUtil.getAllField(targetClass, Access.PRIVATE)) {
			owner.getProperties().put(field.getName(), buldPropertyGroup(owner, field.getName()));
		}
		return owner;
	}

	public static ModelTypeDiffination getModelInfo(Container container, Class<?> _class, TypeModelResource metaSetup) {
		Objects.requireNonNull(metaSetup, "MetaSetup should be required");
		final Class<?>targetClass =_class==null?ClassUtil.getClass(metaSetup.getType()):_class;
		Objects.requireNonNull(targetClass, "Target should be required");
		String id=metaSetup.getId()==null|| metaSetup.getId().equals(Constants.DEFAULT)?targetClass.getSimpleName():metaSetup.getId();
		String name=metaSetup.getName()==null|| metaSetup.getName().equals(Constants.DEFAULT)?targetClass.getSimpleName():metaSetup.getName();
		ModelTypeDiffinationImpl owner=new ModelTypeDiffinationImpl(targetClass,id,name);
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
	
	public static ModelPropertyDiffinationGroup buldPropertyGroup(ModelTypeDiffinationImpl owner,String key, Property setup ) {
		ModelPropertyDiffinationGroupImpl propertyGroup=new  ModelPropertyDiffinationGroupImpl(owner);
		AccessibleObject field=MetaAccessorUtil.findFieldMeta(owner.getType(),key, Access.PRIVATE);
		if(field!=null) {
			ModelPropertyDiffination fieldMeta=buildFieldMeta(owner, field, setup);
			propertyGroup.setFieldMeta(fieldMeta);
		}
		AccessibleObject getter=MetaAccessorUtil.findGetterMeta(owner.getType(),key, Access.PRIVATE);
		if(getter!=null) {
			ModelPropertyDiffination getterMeta=buildFieldMeta(owner, getter, setup);
			propertyGroup.setGetterMeta(getterMeta);
		}
		AccessibleObject setter=MetaAccessorUtil.findSetterMeta(owner.getType(),key, Access.PRIVATE);
		if(setter!=null) {
			ModelPropertyDiffination setterMeta=buildFieldMeta(owner, setter, setup);
			propertyGroup.setSetterMeta(setterMeta);
		}
		return propertyGroup;
	}
	
	public static ModelPropertyDiffinationGroup buldPropertyGroup(ModelTypeDiffinationImpl owner,String key, Relation setup ) {
		ModelPropertyDiffinationGroupImpl propertyGroup=new  ModelPropertyDiffinationGroupImpl(owner);
		AccessibleObject field=MetaAccessorUtil.findFieldMeta(owner.getType(),key, Access.PRIVATE);
		if(field!=null) {
			ModelPropertyDiffination fieldMeta=buildFieldMeta(owner, field, setup);
			propertyGroup.setFieldMeta(fieldMeta);
		}
		AccessibleObject getter=MetaAccessorUtil.findGetterMeta(owner.getType(),key, Access.PRIVATE);
		if(getter!=null) {
			ModelPropertyDiffination getterMeta=buildFieldMeta(owner, getter, setup);
			propertyGroup.setGetterMeta(getterMeta);
		}
		AccessibleObject setter=MetaAccessorUtil.findSetterMeta(owner.getType(),key, Access.PRIVATE);
		if(setter!=null) {
			ModelPropertyDiffination setterMeta=buildFieldMeta(owner, setter, setup);
			propertyGroup.setSetterMeta(setterMeta);
		}
		return propertyGroup;
	}
	
	public static ModelPropertyDiffinationGroup buldPropertyGroup(ModelTypeDiffinationImpl owner,String key, PropertyModelResource<?> setup ) {
		ModelPropertyDiffinationGroupImpl propertyGroup=new  ModelPropertyDiffinationGroupImpl(owner);
		AccessibleObject field=MetaAccessorUtil.findFieldMeta(owner.getType(),key, Access.PRIVATE);
		if(field!=null) {
			ModelPropertyDiffination fieldMeta=buildFieldMeta(owner, field, setup);
			propertyGroup.setFieldMeta(fieldMeta);
		}
		AccessibleObject getter=MetaAccessorUtil.findGetterMeta(owner.getType(),key, Access.PRIVATE);
		if(getter!=null) {
			ModelPropertyDiffination getterMeta=buildFieldMeta(owner, getter, setup);
			propertyGroup.setGetterMeta(getterMeta);
		}
		AccessibleObject setter=MetaAccessorUtil.findSetterMeta(owner.getType(),key, Access.PRIVATE);
		if(setter!=null) {
			ModelPropertyDiffination setterMeta=buildFieldMeta(owner, setter, setup);
			propertyGroup.setSetterMeta(setterMeta);
		}
		return propertyGroup;
	}
	
	public static ModelPropertyDiffinationGroup buldPropertyGroup(ModelTypeDiffinationImpl owner,String key) {
		ModelPropertyDiffinationGroupImpl propertyGroup=new  ModelPropertyDiffinationGroupImpl(owner);
		propertyGroup.setId(key);
		propertyGroup.setName(key);
		AccessibleObject field=MetaAccessorUtil.findFieldMeta(owner.getType(),key, Access.PRIVATE);
		if(field!=null) {
			ModelPropertyDiffination fieldMeta=buildFieldMeta(owner, field);
			propertyGroup.setFieldMeta(fieldMeta);
		}
		AccessibleObject getter=MetaAccessorUtil.findGetterMeta(owner.getType(),key, Access.PRIVATE);
		if(getter!=null) {
			ModelPropertyDiffination getterMeta=buildFieldMeta(owner, getter);
			propertyGroup.setGetterMeta(getterMeta);
		}
		AccessibleObject setter=MetaAccessorUtil.findSetterMeta(owner.getType(),key, Access.PRIVATE);
		if(setter!=null) {
			ModelPropertyDiffination setterMeta=buildFieldMeta(owner, setter);
			propertyGroup.setSetterMeta(setterMeta);
		}
		return propertyGroup;
	}

	public static ModelTypeDiffination getModelInfo(Container container,Class<?> target, Model metaSetup) {
		Objects.requireNonNull(target, "Target should be required");
		Objects.requireNonNull(metaSetup, "Meta should be required");
		String id=metaSetup.id()==null|| metaSetup.id().equals(Constants.DEFAULT)?target.getSimpleName():metaSetup.id();
		String name=target.getSimpleName();
		ModelTypeDiffinationImpl owner=new ModelTypeDiffinationImpl(target,id,name);
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
	
	public static ModelConstructorDiffination getConsMetaInfo(ModelTypeDiffinationImpl owner) {
		ModelConstructorDiffinationImpl consMetaInfo=new ModelConstructorDiffinationImpl(owner);
		consMetaInfo.setAccess(owner.getAccess());
		consMetaInfo.setOwner(owner);
		consMetaInfo.setTarget(ConstructUtil.getConstructor(owner.getType(), owner.getAccess(), consMetaInfo.getArguments()));
		return consMetaInfo;
	}
	
	public static ModelConstructorDiffinationImpl getConsMetaInfo(ModelTypeDiffinationImpl owner,Construct constructor) {
		ModelConstructorDiffinationImpl consMetaInfo=new ModelConstructorDiffinationImpl(owner);
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
		consMetaInfo.setTarget(ConstructUtil.getConstructor(owner.getType(), constructor.access(), consMetaInfo.getArguments()));
		return consMetaInfo;
	}

	private static ModelConstructorDiffination getConsMetaInfo(ModelTypeDiffinationImpl owner, ConstructorModelResource constructor) {
		ModelConstructorDiffinationImpl consMetaInfo=new ModelConstructorDiffinationImpl(owner);
		consMetaInfo.setAccess(Access.valueOf(constructor.getAccess()));
		consMetaInfo.setOwner(owner);
		if(constructor.getParameterList()!=null) {
			Type[] arguments=new Type[constructor.getParameterList().size()];
			Object[] values=new Object[constructor.getParameterList().size()];
			AtomicInteger index=new AtomicInteger(0);
			constructor.getParameterList().stream().sorted((param1,param2)->{
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
		consMetaInfo.setTarget(ConstructUtil.getConstructor(owner.getType(), Access.valueOf(constructor.getAccess()), consMetaInfo.getArguments()));
		return consMetaInfo;
	}
	
	/*
	 * Default property builder
	 */
	
	public static ModelPropertyDiffination buildFieldMeta(ModelTypeDiffination owner, AccessibleObject target) {
		if (target instanceof Field) {
			return buildFieldMeta(owner, (Field) target);
		} else {
			return buildFieldMeta(owner, (Method) target);
		}
	}

	public static ModelPropertyDiffination buildFieldMeta(ModelTypeDiffination owner,Method target) {
		ModelPropertyDiffinationImpl metaInfo = new ModelPropertyDiffinationImpl(owner, target);
		metaInfo.setId(target.getName());
		metaInfo.setName(target.getName());
		metaInfo.setAccess(Access.PRIVATE);
		metaInfo.setRequired(false);
		return metaInfo;
	}

	public static ModelPropertyDiffination buildFieldMeta(ModelTypeDiffination owner, Field target) {
		ModelRelationDiffinationImpl metaInfo = new ModelRelationDiffinationImpl(owner, target);
		metaInfo.setId(target.getName());
		metaInfo.setName(target.getName());
		metaInfo.setAccess(Access.valueOf(target.getModifiers()));
		metaInfo.setRequired(false);
		return metaInfo;
	}

	public static ModelPropertyDiffination buildFieldMeta(ModelTypeDiffination owner, AccessibleObject target, Property property) {
		if (target instanceof Field) {
			return buildFieldMeta(owner, (Field) target, property);
		} else {
			return buildFieldMeta(owner, (Method) target, property);
		}
	}
	
	public static ModelPropertyDiffination buildFieldMeta(ModelTypeDiffination owner, Field target,Property property) {
		ModelPropertyDiffinationImpl metaInfo = new ModelPropertyDiffinationImpl(owner, target);
		String id = property.id() == null || property.id().equals(Constants.DEFAULT)? target.getName() : property.id();
		metaInfo.setId(id);
		metaInfo.setName(target.getName());
		metaInfo.setAccess(property.access());
		metaInfo.setValue(property.value());
		metaInfo.setRequired(property.required());
		return metaInfo;
	}
	
	public static ModelPropertyDiffination buildFieldMeta(ModelTypeDiffination owner, Method target,Property property) {
		ModelPropertyDiffinationImpl metaInfo = new ModelPropertyDiffinationImpl(owner, target);
		String id = property.id() == null || property.id().equals(Constants.DEFAULT)? target.getName() : property.id();
		metaInfo.setId(id);
		metaInfo.setName(target.getName());
		metaInfo.setAccess(property.access());
		metaInfo.setValue(property.value());
		metaInfo.setRequired(property.required());
		return metaInfo;
	}

	public static ModelPropertyDiffination buildFieldMeta(ModelTypeDiffination owner, AccessibleObject target, PropertyModelResource<?> metaSetup) {
		if (target instanceof Field) {
			return buildFieldMeta(owner, (Field) target, metaSetup);
		} else {
			return buildFieldMeta(owner, (Method) target, metaSetup);
		}
	}
	
	public static ModelPropertyDiffination buildFieldMeta(ModelTypeDiffination owner, Method target,PropertyModelResource<?> property) {
		ModelPropertyDiffinationImpl metaInfo = new ModelPropertyDiffinationImpl(owner, target);
		String id = property.getId() == null || property.getId().equals(Constants.DEFAULT)? target.getName() : property.getId();
		metaInfo.setId(id);
		metaInfo.setName(target.getName());
		metaInfo.setAccess(Access.valueOf(property.getAccess()));
		metaInfo.setValue(property.getValue());
		metaInfo.setRequired(property.isRequired());
		return metaInfo;
	}
	
	public static ModelPropertyDiffination buildFieldMeta(ModelTypeDiffination owner, Field target,PropertyModelResource<?> property) {
		ModelPropertyDiffinationImpl metaInfo = new ModelPropertyDiffinationImpl(owner, target);
		String id = property.getId() == null || property.getId().equals(Constants.DEFAULT)? target.getName() : property.getId();
		metaInfo.setId(id);
		metaInfo.setName(target.getName());
		metaInfo.setAccess(Access.valueOf(property.getAccess()));
		metaInfo.setRequired(property.isRequired());
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
	
	public static ModelRelationDiffination buildFieldMeta(ModelTypeDiffination owner, AccessibleObject target,RelationPropertyModelResource<?> metaSetup) {
		if (target instanceof Field) {
			return buildFieldMeta(owner, (Field) target, metaSetup);
		} else {
			return buildFieldMeta(owner, (Method) target, metaSetup);
		}
	}
	
	public static ModelRelationDiffination buildFieldMeta(ModelTypeDiffination owner, Field target,RelationPropertyModelResource<?> property) {
		ModelRelationDiffinationImpl metaInfo = new ModelRelationDiffinationImpl(owner, target);
		String id = property.getId() == null || property.getId().equals(Constants.DEFAULT)? target.getName() : property.getId();
		metaInfo.setId(id);
		metaInfo.setName(target.getName());
		metaInfo.setAccess(Access.valueOf(property.getAccess()));
		metaInfo.setValue(property.getValue());
		metaInfo.setRequired(property.isRequired());
		return metaInfo;
	}
	

	public static ModelRelationDiffination buildFieldMeta(ModelTypeDiffination owner, Method target,RelationPropertyModelResource<?> property) {
		String id = property.getId() == null || property.getId().equals(Constants.DEFAULT)? target.getName() : property.getId();
		ModelRelationDiffinationImpl metaInfo = new ModelRelationDiffinationImpl(owner, target);
		metaInfo.setId(id);
		metaInfo.setName(target.getName());
		metaInfo.setAccess(Access.valueOf(property.getAccess()));
		metaInfo.setValue(property.getValue());
		metaInfo.setRequired(property.isRequired());
		return metaInfo;
	}
	
	
	public static ModelRelationDiffination buildFieldMeta(ModelTypeDiffination owner, AccessibleObject target, Relation property) {
		if (target instanceof Field) {
			return buildFieldMeta(owner, (Field) target, property);
		} else {
			return buildFieldMeta(owner, (Method) target, property);
		}
	}
	
	public static ModelRelationDiffination buildFieldMeta(ModelTypeDiffination owner, Field target,Relation property) {
		ModelRelationDiffinationImpl metaInfo = new ModelRelationDiffinationImpl(owner, target);
		String id = property.id() == null || property.id().equals(Constants.DEFAULT)? target.getName() : property.id();
		metaInfo.setId(id);
		metaInfo.setName(target.getName());
		metaInfo.setAccess(property.access());
		metaInfo.setRequired(property.required());
		return metaInfo;
	}
	
	public static ModelRelationDiffinationImpl buildFieldMeta(ModelTypeDiffination owner, Method target,Relation property) {
		ModelRelationDiffinationImpl metaInfo = new ModelRelationDiffinationImpl(owner, target);
		String id = property.id() == null || property.id().equals(Constants.DEFAULT)? target.getName() : property.id();
		metaInfo.setId(id);
		metaInfo.setName(target.getName());
		metaInfo.setAccess(property.access());
		metaInfo.setRequired(property.required());
		return metaInfo;
	}
	
	
}
