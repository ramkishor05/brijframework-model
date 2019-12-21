package org.brijframework.model.helper;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import org.brijframework.Access;
import org.brijframework.container.Container;
import org.brijframework.model.diffination.ModelConstructorDiffination;
import org.brijframework.model.diffination.ModelParameterDiffination;
import org.brijframework.model.diffination.ModelPropertyDiffination;
import org.brijframework.model.diffination.ModelPropertyDiffinationGroup;
import org.brijframework.model.diffination.ModelRelationDiffination;
import org.brijframework.model.diffination.ModelTypeDeffination;
import org.brijframework.model.diffination.impl.ModelConstructorDiffinationImpl;
import org.brijframework.model.diffination.impl.ModelParameterDiffinationImpl;
import org.brijframework.model.diffination.impl.ModelPropertyDiffinationGroupImpl;
import org.brijframework.model.diffination.impl.ModelPropertyDiffinationImpl;
import org.brijframework.model.diffination.impl.ModelRelationDiffinationImpl;
import org.brijframework.model.diffination.impl.ModelTypeDiffinationImpl;
import org.brijframework.model.resource.ConstructorModelResource;
import org.brijframework.model.resource.ParameterModelResource;
import org.brijframework.model.resource.PropertyModelResource;
import org.brijframework.model.resource.RelationPropertyModelResource;
import org.brijframework.model.resource.TypeModelResource;
import org.brijframework.support.model.Model;
import org.brijframework.support.model.ModelConstruct;
import org.brijframework.support.model.properties.ModelProperty;
import org.brijframework.support.model.properties.ModelRelation;
import org.brijframework.util.accessor.MetaAccessorUtil;
import org.brijframework.util.reflect.ClassUtil;
import org.brijframework.util.reflect.ConstructUtil;
import org.brijframework.util.reflect.FieldUtil;
import org.brijframework.util.support.Constants;
import org.brijframework.util.support.ReflectionAccess;

public class MetaDataHelper {
	
	public static ModelTypeDeffination getModelInfo(Container container, Class<?> targetClass) {
		String id=targetClass.getSimpleName();
		String name=targetClass.getSimpleName();
		ModelTypeDiffinationImpl owner=new ModelTypeDiffinationImpl(targetClass,id,name);
		owner.setAccess(Access.AUTO);
		owner.setConstructor(getConstructorDeffination(owner));
		for (Field field : FieldUtil.getAllField(targetClass, ReflectionAccess.PRIVATE)) {
			owner.getProperties().put(field.getName(), buldPropertyGroup(owner, field.getName()));
		}
		return owner;
	}

	public static ModelTypeDeffination getModelTypeDiffination(Container container, Class<?> _class, TypeModelResource typeModelResource) {
		Objects.requireNonNull(typeModelResource, "MetaSetup should be required");
		final Class<?>targetClass =_class==null?ClassUtil.getClass(typeModelResource.getType()):_class;
		Objects.requireNonNull(targetClass, "Target should be required");
		String id=typeModelResource.getId()==null|| typeModelResource.getId().equals(Constants.DEFAULT)?targetClass.getSimpleName():typeModelResource.getId();
		String name=typeModelResource.getName()==null|| typeModelResource.getName().equals(Constants.DEFAULT)?targetClass.getSimpleName():typeModelResource.getName();
		ModelTypeDiffinationImpl owner=new ModelTypeDiffinationImpl(targetClass,id,name);
		owner.setAccess(Access.findValue(typeModelResource.getAccess()));
		if(typeModelResource.getConstructor()!=null) {
			owner.setConstructor(getConstructorDeffination(owner, typeModelResource.getConstructor()));
		}else {
			owner.setConstructor(getConstructorDeffination(owner));
		}
		if(typeModelResource.getProperties()!=null) {
			typeModelResource.getProperties().forEach((key,propertyModelResource)->{
				owner.getProperties().put(key, buldPropertyGroup(owner, key, propertyModelResource));
			});
		}
		return owner;
	}
	
	public static ModelPropertyDiffinationGroup buldPropertyGroup(ModelTypeDiffinationImpl owner,String key, ModelProperty setup ) {
		ModelPropertyDiffinationGroupImpl propertyGroup=new  ModelPropertyDiffinationGroupImpl(owner);
		AccessibleObject field=MetaAccessorUtil.findFieldMeta(owner.getType(),key, ReflectionAccess.PRIVATE);
		if(field!=null) {
			ModelPropertyDiffination fieldMeta=buildFieldMeta(owner, field, setup);
			propertyGroup.setFieldMeta(fieldMeta);
		}
		AccessibleObject getter=MetaAccessorUtil.findGetterMeta(owner.getType(),key, ReflectionAccess.PRIVATE);
		if(getter!=null) {
			ModelPropertyDiffination getterMeta=buildFieldMeta(owner, getter, setup);
			propertyGroup.setGetterMeta(getterMeta);
		}
		AccessibleObject setter=MetaAccessorUtil.findSetterMeta(owner.getType(),key, ReflectionAccess.PRIVATE);
		if(setter!=null) {
			ModelPropertyDiffination setterMeta=buildFieldMeta(owner, setter, setup);
			propertyGroup.setSetterMeta(setterMeta);
		}
		return propertyGroup;
	}
	
	public static ModelPropertyDiffinationGroup buldPropertyGroup(ModelTypeDiffinationImpl owner,String key, ModelRelation setup ) {
		ModelPropertyDiffinationGroupImpl propertyGroup=new  ModelPropertyDiffinationGroupImpl(owner);
		AccessibleObject field=MetaAccessorUtil.findFieldMeta(owner.getType(),key, ReflectionAccess.PRIVATE);
		if(field!=null) {
			ModelPropertyDiffination fieldMeta=buildFieldMeta(owner, field, setup);
			propertyGroup.setFieldMeta(fieldMeta);
		}
		AccessibleObject getter=MetaAccessorUtil.findGetterMeta(owner.getType(),key, ReflectionAccess.PRIVATE);
		if(getter!=null) {
			ModelPropertyDiffination getterMeta=buildFieldMeta(owner, getter, setup);
			propertyGroup.setGetterMeta(getterMeta);
		}
		AccessibleObject setter=MetaAccessorUtil.findSetterMeta(owner.getType(),key, ReflectionAccess.PRIVATE);
		if(setter!=null) {
			ModelPropertyDiffination setterMeta=buildFieldMeta(owner, setter, setup);
			propertyGroup.setSetterMeta(setterMeta);
		}
		return propertyGroup;
	}
	
	public static ModelPropertyDiffinationGroup buldPropertyGroup(ModelTypeDiffinationImpl owner,String key, PropertyModelResource<?> propertyModelResource ) {
		ModelPropertyDiffinationGroupImpl propertyGroup=new  ModelPropertyDiffinationGroupImpl(owner);
		AccessibleObject field=MetaAccessorUtil.findFieldMeta(owner.getType(),key, ReflectionAccess.PRIVATE);
		if(field!=null) {
			ModelPropertyDiffination fieldMeta=buildFieldMeta(owner, field, propertyModelResource);
			propertyGroup.setFieldMeta(fieldMeta);
		}
		AccessibleObject getter=MetaAccessorUtil.findGetterMeta(owner.getType(),key, ReflectionAccess.PRIVATE);
		if(getter!=null) {
			ModelPropertyDiffination getterMeta=buildFieldMeta(owner, getter, propertyModelResource);
			propertyGroup.setGetterMeta(getterMeta);
		}
		AccessibleObject setter=MetaAccessorUtil.findSetterMeta(owner.getType(),key, ReflectionAccess.PRIVATE);
		if(setter!=null) {
			ModelPropertyDiffination setterMeta=buildFieldMeta(owner, setter, propertyModelResource);
			propertyGroup.setSetterMeta(setterMeta);
		}
		return propertyGroup;
	}
	
	public static ModelPropertyDiffinationGroup buldPropertyGroup(ModelTypeDiffinationImpl owner,String key) {
		ModelPropertyDiffinationGroupImpl propertyGroup=new  ModelPropertyDiffinationGroupImpl(owner);
		propertyGroup.setId(key);
		propertyGroup.setName(key);
		AccessibleObject field=MetaAccessorUtil.findFieldMeta(owner.getType(),key, ReflectionAccess.PRIVATE);
		if(field!=null) {
			ModelPropertyDiffination fieldMeta=buildFieldMeta(owner, field);
			propertyGroup.setFieldMeta(fieldMeta);
		}
		AccessibleObject getter=MetaAccessorUtil.findGetterMeta(owner.getType(),key, ReflectionAccess.PRIVATE);
		if(getter!=null) {
			ModelPropertyDiffination getterMeta=buildFieldMeta(owner, getter);
			propertyGroup.setGetterMeta(getterMeta);
		}
		AccessibleObject setter=MetaAccessorUtil.findSetterMeta(owner.getType(),key, ReflectionAccess.PRIVATE);
		if(setter!=null) {
			ModelPropertyDiffination setterMeta=buildFieldMeta(owner, setter);
			propertyGroup.setSetterMeta(setterMeta);
		}
		return propertyGroup;
	}

	public static ModelTypeDeffination getModelInfo(Container container,Class<?> target, Model metaSetup) {
		Objects.requireNonNull(target, "Target should be required");
		Objects.requireNonNull(metaSetup, "Meta should be required");
		String id=metaSetup.id()==null|| metaSetup.id().equals(Constants.DEFAULT)?target.getSimpleName():metaSetup.id();
		String name=target.getSimpleName();
		ModelTypeDiffinationImpl owner=new ModelTypeDiffinationImpl(target,id,name);
		owner.setAccess(metaSetup.access()!=null?metaSetup.access():Access.AUTO);
		if(metaSetup.constructor()!=null) {
			owner.setConstructor(getConsMetaInfo(owner, metaSetup.constructor()));
		}else {
			owner.setConstructor(getConstructorDeffination(owner));
		}
		List<Field> properties= FieldUtil.getAllField(target,ReflectionAccess.PRIVATE_NO_STATIC_FINAL);
		for(Field targetField:properties) {
			if(targetField.isAnnotationPresent(ModelProperty.class)) {
				owner.getProperties().put(targetField.getName(), buldPropertyGroup(owner, targetField.getName(), (ModelProperty) targetField.getAnnotation(ModelProperty.class)));
			}else if(targetField.isAnnotationPresent(ModelRelation.class)) {
				owner.getProperties().put(targetField.getName(), buldPropertyGroup(owner, targetField.getName(), (ModelRelation) targetField.getAnnotation(ModelRelation.class)));
			}else {
				owner.getProperties().put(targetField.getName(), buldPropertyGroup(owner, targetField.getName()));
			}
		}
		return owner;
	}
	
	/*
	 * Constructor builder
	 */
	
	public static ModelConstructorDiffination getConstructorDeffination(ModelTypeDiffinationImpl owner) {
		ModelConstructorDiffinationImpl consMetaInfo=new ModelConstructorDiffinationImpl(owner);
		consMetaInfo.setAccess(owner.getAccess());
		consMetaInfo.setOwner(owner);
		consMetaInfo.setTarget(ConstructUtil.getConstructor(owner.getType(), ReflectionAccess.PRIVATE, consMetaInfo.getArguments()));
		return consMetaInfo;
	}
	
	public static ModelConstructorDiffinationImpl getConsMetaInfo(ModelTypeDiffinationImpl owner,ModelConstruct constructor) {
		ModelConstructorDiffinationImpl consMetaInfo=new ModelConstructorDiffinationImpl(owner);
		consMetaInfo.setAccess(constructor.access());
		consMetaInfo.setOwner(owner);
		if(constructor.params()!=null) {
			Type[] arguments=new Type[constructor.params().length];
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
			});
			consMetaInfo.setArguments(arguments);
		}
		consMetaInfo.setTarget(ConstructUtil.getConstructor(owner.getType(), ReflectionAccess.PRIVATE, consMetaInfo.getArguments()));
		return consMetaInfo;
	}

	private static ModelConstructorDiffination getConstructorDeffination(ModelTypeDiffinationImpl owner, ConstructorModelResource<?> constructor) {
		ModelConstructorDiffinationImpl constructorDiffination=new ModelConstructorDiffinationImpl(owner);
		constructorDiffination.setAccess(Access.findValue(constructor.getAccess()));
		constructorDiffination.setOwner(owner);
		if(constructor.getParameterList()!=null) {
			Type[] arguments=new Type[constructor.getParameterList().size()];
			constructor.getParameterList().stream().sorted((param1,param2)->{
				if(param1.getIndex()>param2.getIndex()) {
					return 1;
				}
				if(param1.getIndex()<param2.getIndex()) {
					return -1;
				}
				return 0;
			}).forEach(param->{
				arguments[param.getIndex()]= ClassUtil.getClass(param.getType());
			});
			constructorDiffination.setArguments(arguments);
		}
		try {
			constructorDiffination.setTarget(ConstructUtil.getConstructor(owner.getType(), ReflectionAccess.PRIVATE, constructorDiffination.getArguments()));
			for(ParameterModelResource param :constructor.getParameterList()) {
			   constructorDiffination.getParameters().add(getParameter(constructorDiffination,param));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return constructorDiffination;
	}
	
	/*
	 * Default property builder
	 */
	
	private static ModelParameterDiffination<?> getParameter(ModelConstructorDiffinationImpl constructorDiffination,
			ParameterModelResource param) {
		ModelParameterDiffinationImpl diffinationImpl=new ModelParameterDiffinationImpl();
		diffinationImpl.setType(constructorDiffination.getType().getParameters()[param.getIndex()]);
		return diffinationImpl;
	}

	public static ModelPropertyDiffination buildFieldMeta(ModelTypeDeffination owner, AccessibleObject target) {
		if (target instanceof Field) {
			return buildFieldMeta(owner, (Field) target);
		} else {
			return buildFieldMeta(owner, (Method) target);
		}
	}

	public static ModelPropertyDiffination buildFieldMeta(ModelTypeDeffination owner,Method target) {
		ModelPropertyDiffinationImpl metaInfo = new ModelPropertyDiffinationImpl(owner, target);
		metaInfo.setId(target.getName());
		metaInfo.setName(target.getName());
		metaInfo.setAccess(Access.AUTO);
		metaInfo.setRequired(false);
		return metaInfo;
	}

	public static ModelPropertyDiffination buildFieldMeta(ModelTypeDeffination owner, Field target) {
		ModelRelationDiffinationImpl metaInfo = new ModelRelationDiffinationImpl(owner, target);
		metaInfo.setId(target.getName());
		metaInfo.setName(target.getName());
		metaInfo.setAccess(Access.AUTO);
		metaInfo.setRequired(false);
		return metaInfo;
	}

	public static ModelPropertyDiffination buildFieldMeta(ModelTypeDeffination owner, AccessibleObject target, ModelProperty property) {
		if (target instanceof Field) {
			return buildFieldMeta(owner, (Field) target, property);
		} else {
			return buildFieldMeta(owner, (Method) target, property);
		}
	}
	
	public static ModelPropertyDiffination buildFieldMeta(ModelTypeDeffination owner, Field target,ModelProperty property) {
		ModelPropertyDiffinationImpl metaInfo = new ModelPropertyDiffinationImpl(owner, target);
		String id = property.id() == null || property.id().equals(Constants.DEFAULT)? target.getName() : property.id();
		metaInfo.setId(id);
		metaInfo.setName(target.getName());
		metaInfo.setAccess(property.access());
		metaInfo.setRequired(property.required());
		return metaInfo;
	}
	
	public static ModelPropertyDiffination buildFieldMeta(ModelTypeDeffination owner, Method target,ModelProperty property) {
		ModelPropertyDiffinationImpl metaInfo = new ModelPropertyDiffinationImpl(owner, target);
		String id = property.id() == null || property.id().equals(Constants.DEFAULT)? target.getName() : property.id();
		metaInfo.setId(id);
		metaInfo.setName(target.getName());
		metaInfo.setAccess(property.access());
		metaInfo.setRequired(property.required());
		return metaInfo;
	}

	public static ModelPropertyDiffination buildFieldMeta(ModelTypeDeffination owner, AccessibleObject target, PropertyModelResource<?> metaSetup) {
		if (target instanceof Field) {
			return buildFieldMeta(owner, (Field) target, metaSetup);
		} else {
			return buildFieldMeta(owner, (Method) target, metaSetup);
		}
	}
	
	public static ModelPropertyDiffination buildFieldMeta(ModelTypeDeffination owner, Method target,PropertyModelResource<?> property) {
		ModelPropertyDiffinationImpl metaInfo = new ModelPropertyDiffinationImpl(owner, target);
		String id = property.getId() == null || property.getId().equals(Constants.DEFAULT)? target.getName() : property.getId();
		metaInfo.setId(id);
		metaInfo.setName(target.getName());
		metaInfo.setAccess(Access.findValue(property.getAccess()));
		metaInfo.setRequired(property.isRequired());
		return metaInfo;
	}
	
	public static ModelPropertyDiffination buildFieldMeta(ModelTypeDeffination owner, Field target,PropertyModelResource<?> property) {
		ModelPropertyDiffinationImpl metaInfo = new ModelPropertyDiffinationImpl(owner, target);
		String id = property.getId() == null || property.getId().equals(Constants.DEFAULT)? target.getName() : property.getId();
		metaInfo.setId(id);
		metaInfo.setName(target.getName());
		metaInfo.setAccess(Access.findValue(property.getAccess()));
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
	
	public static ModelRelationDiffination buildFieldMeta(ModelTypeDeffination owner, AccessibleObject target,RelationPropertyModelResource<?> metaSetup) {
		if (target instanceof Field) {
			return buildFieldMeta(owner, (Field) target, metaSetup);
		} else {
			return buildFieldMeta(owner, (Method) target, metaSetup);
		}
	}
	
	public static ModelRelationDiffination buildFieldMeta(ModelTypeDeffination owner, Field target,RelationPropertyModelResource<?> property) {
		ModelRelationDiffinationImpl metaInfo = new ModelRelationDiffinationImpl(owner, target);
		String id = property.getId() == null || property.getId().equals(Constants.DEFAULT)? target.getName() : property.getId();
		metaInfo.setId(id);
		metaInfo.setName(target.getName());
		metaInfo.setAccess(Access.findValue(property.getAccess()));
		metaInfo.setRequired(property.isRequired());
		return metaInfo;
	}
	

	public static ModelRelationDiffination buildFieldMeta(ModelTypeDeffination owner, Method target,RelationPropertyModelResource<?> property) {
		String id = property.getId() == null || property.getId().equals(Constants.DEFAULT)? target.getName() : property.getId();
		ModelRelationDiffinationImpl metaInfo = new ModelRelationDiffinationImpl(owner, target);
		metaInfo.setId(id);
		metaInfo.setName(target.getName());
		metaInfo.setAccess(Access.findValue(property.getAccess()));
		metaInfo.setRequired(property.isRequired());
		return metaInfo;
	}
	
	
	public static ModelRelationDiffination buildFieldMeta(ModelTypeDeffination owner, AccessibleObject target, ModelRelation property) {
		if (target instanceof Field) {
			return buildFieldMeta(owner, (Field) target, property);
		} else {
			return buildFieldMeta(owner, (Method) target, property);
		}
	}
	
	public static ModelRelationDiffination buildFieldMeta(ModelTypeDeffination owner, Field target,ModelRelation property) {
		ModelRelationDiffinationImpl metaInfo = new ModelRelationDiffinationImpl(owner, target);
		String id = property.id() == null || property.id().equals(Constants.DEFAULT)? target.getName() : property.id();
		metaInfo.setId(id);
		metaInfo.setName(target.getName());
		metaInfo.setAccess(property.access());
		metaInfo.setRequired(property.required());
		return metaInfo;
	}
	
	public static ModelRelationDiffinationImpl buildFieldMeta(ModelTypeDeffination owner, Method target,ModelRelation property) {
		ModelRelationDiffinationImpl metaInfo = new ModelRelationDiffinationImpl(owner, target);
		String id = property.id() == null || property.id().equals(Constants.DEFAULT)? target.getName() : property.id();
		metaInfo.setId(id);
		metaInfo.setName(target.getName());
		metaInfo.setAccess(property.access());
		metaInfo.setRequired(property.required());
		return metaInfo;
	}
	
	
}
