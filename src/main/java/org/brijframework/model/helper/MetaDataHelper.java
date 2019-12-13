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
import org.brijframework.model.diffination.ConstructorModelMetaData;
import org.brijframework.model.diffination.PropertyModelMetaData;
import org.brijframework.model.diffination.PropertyModelMetaDataGroup;
import org.brijframework.model.diffination.RelationPropertyModelMetaData;
import org.brijframework.model.diffination.TypeModelDiffination;
import org.brijframework.model.diffination.impl.ConstructorModelMetaDataObject;
import org.brijframework.model.diffination.impl.PropertyModelMetaDataGroupObject;
import org.brijframework.model.diffination.impl.PropertyModelMetaDataObject;
import org.brijframework.model.diffination.impl.RelationPropertyModelMetaDataObject;
import org.brijframework.model.diffination.impl.TypeModelMetaDataImpl;
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
	
	public static TypeModelDiffination getModelInfo(Container container, Class<?> targetClass) {
		String id=targetClass.getSimpleName();
		String name=targetClass.getSimpleName();
		TypeModelMetaDataImpl owner=new TypeModelMetaDataImpl(targetClass,id,name);
		owner.setAccess(Access.PUBLIC);
		owner.setConstructor(getConsMetaInfo(owner));
		for (Field field : FieldUtil.getAllField(targetClass, Access.PRIVATE)) {
			owner.getProperties().put(field.getName(), buldPropertyGroup(owner, field.getName()));
		}
		return owner;
	}

	public static TypeModelDiffination getModelInfo(Container container, Class<?> _class, TypeModelResource metaSetup) {
		Objects.requireNonNull(metaSetup, "MetaSetup should be required");
		final Class<?>targetClass =_class==null?ClassUtil.getClass(metaSetup.getType()):_class;
		Objects.requireNonNull(targetClass, "Target should be required");
		String id=metaSetup.getId()==null|| metaSetup.getId().equals(Constants.DEFAULT)?targetClass.getSimpleName():metaSetup.getId();
		String name=metaSetup.getName()==null|| metaSetup.getName().equals(Constants.DEFAULT)?targetClass.getSimpleName():metaSetup.getName();
		TypeModelMetaDataImpl owner=new TypeModelMetaDataImpl(targetClass,id,name);
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
	
	public static PropertyModelMetaDataGroup buldPropertyGroup(TypeModelMetaDataImpl owner,String key, Property setup ) {
		PropertyModelMetaDataGroupObject propertyGroup=new  PropertyModelMetaDataGroupObject(owner);
		AccessibleObject field=MetaAccessorUtil.findFieldMeta(owner.getType(),key, Access.PRIVATE);
		if(field!=null) {
			PropertyModelMetaData fieldMeta=buildFieldMeta(owner, field, setup);
			propertyGroup.setFieldMeta(fieldMeta);
		}
		AccessibleObject getter=MetaAccessorUtil.findGetterMeta(owner.getType(),key, Access.PRIVATE);
		if(getter!=null) {
			PropertyModelMetaData getterMeta=buildFieldMeta(owner, getter, setup);
			propertyGroup.setGetterMeta(getterMeta);
		}
		AccessibleObject setter=MetaAccessorUtil.findSetterMeta(owner.getType(),key, Access.PRIVATE);
		if(setter!=null) {
			PropertyModelMetaData setterMeta=buildFieldMeta(owner, setter, setup);
			propertyGroup.setSetterMeta(setterMeta);
		}
		return propertyGroup;
	}
	
	public static PropertyModelMetaDataGroup buldPropertyGroup(TypeModelMetaDataImpl owner,String key, Relation setup ) {
		PropertyModelMetaDataGroupObject propertyGroup=new  PropertyModelMetaDataGroupObject(owner);
		AccessibleObject field=MetaAccessorUtil.findFieldMeta(owner.getType(),key, Access.PRIVATE);
		if(field!=null) {
			PropertyModelMetaData fieldMeta=buildFieldMeta(owner, field, setup);
			propertyGroup.setFieldMeta(fieldMeta);
		}
		AccessibleObject getter=MetaAccessorUtil.findGetterMeta(owner.getType(),key, Access.PRIVATE);
		if(getter!=null) {
			PropertyModelMetaData getterMeta=buildFieldMeta(owner, getter, setup);
			propertyGroup.setGetterMeta(getterMeta);
		}
		AccessibleObject setter=MetaAccessorUtil.findSetterMeta(owner.getType(),key, Access.PRIVATE);
		if(setter!=null) {
			PropertyModelMetaData setterMeta=buildFieldMeta(owner, setter, setup);
			propertyGroup.setSetterMeta(setterMeta);
		}
		return propertyGroup;
	}
	
	public static PropertyModelMetaDataGroup buldPropertyGroup(TypeModelMetaDataImpl owner,String key, PropertyModelResource<?> setup ) {
		PropertyModelMetaDataGroupObject propertyGroup=new  PropertyModelMetaDataGroupObject(owner);
		AccessibleObject field=MetaAccessorUtil.findFieldMeta(owner.getType(),key, Access.PRIVATE);
		if(field!=null) {
			PropertyModelMetaData fieldMeta=buildFieldMeta(owner, field, setup);
			propertyGroup.setFieldMeta(fieldMeta);
		}
		AccessibleObject getter=MetaAccessorUtil.findGetterMeta(owner.getType(),key, Access.PRIVATE);
		if(getter!=null) {
			PropertyModelMetaData getterMeta=buildFieldMeta(owner, getter, setup);
			propertyGroup.setGetterMeta(getterMeta);
		}
		AccessibleObject setter=MetaAccessorUtil.findSetterMeta(owner.getType(),key, Access.PRIVATE);
		if(setter!=null) {
			PropertyModelMetaData setterMeta=buildFieldMeta(owner, setter, setup);
			propertyGroup.setSetterMeta(setterMeta);
		}
		return propertyGroup;
	}
	
	public static PropertyModelMetaDataGroup buldPropertyGroup(TypeModelMetaDataImpl owner,String key) {
		PropertyModelMetaDataGroupObject propertyGroup=new  PropertyModelMetaDataGroupObject(owner);
		propertyGroup.setId(key);
		propertyGroup.setName(key);
		AccessibleObject field=MetaAccessorUtil.findFieldMeta(owner.getType(),key, Access.PRIVATE);
		if(field!=null) {
			PropertyModelMetaData fieldMeta=buildFieldMeta(owner, field);
			propertyGroup.setFieldMeta(fieldMeta);
		}
		AccessibleObject getter=MetaAccessorUtil.findGetterMeta(owner.getType(),key, Access.PRIVATE);
		if(getter!=null) {
			PropertyModelMetaData getterMeta=buildFieldMeta(owner, getter);
			propertyGroup.setGetterMeta(getterMeta);
		}
		AccessibleObject setter=MetaAccessorUtil.findSetterMeta(owner.getType(),key, Access.PRIVATE);
		if(setter!=null) {
			PropertyModelMetaData setterMeta=buildFieldMeta(owner, setter);
			propertyGroup.setSetterMeta(setterMeta);
		}
		return propertyGroup;
	}

	public static TypeModelDiffination getModelInfo(Container container,Class<?> target, Model metaSetup) {
		Objects.requireNonNull(target, "Target should be required");
		Objects.requireNonNull(metaSetup, "Meta should be required");
		String id=metaSetup.id()==null|| metaSetup.id().equals(Constants.DEFAULT)?target.getSimpleName():metaSetup.id();
		String name=target.getSimpleName();
		TypeModelMetaDataImpl owner=new TypeModelMetaDataImpl(target,id,name);
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
	
	public static ConstructorModelMetaData getConsMetaInfo(TypeModelMetaDataImpl owner) {
		ConstructorModelMetaDataObject consMetaInfo=new ConstructorModelMetaDataObject(owner);
		consMetaInfo.setAccess(owner.getAccess());
		consMetaInfo.setOwner(owner);
		consMetaInfo.setTarget(ConstructUtil.getConstructor(owner.getType(), owner.getAccess(), consMetaInfo.getArguments()));
		return consMetaInfo;
	}
	
	public static ConstructorModelMetaDataObject getConsMetaInfo(TypeModelMetaDataImpl owner,Construct constructor) {
		ConstructorModelMetaDataObject consMetaInfo=new ConstructorModelMetaDataObject(owner);
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

	private static ConstructorModelMetaData getConsMetaInfo(TypeModelMetaDataImpl owner, ConstructorModelResource constructor) {
		ConstructorModelMetaDataObject consMetaInfo=new ConstructorModelMetaDataObject(owner);
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
	
	public static PropertyModelMetaData buildFieldMeta(TypeModelDiffination owner, AccessibleObject target) {
		if (target instanceof Field) {
			return buildFieldMeta(owner, (Field) target);
		} else {
			return buildFieldMeta(owner, (Method) target);
		}
	}

	public static PropertyModelMetaData buildFieldMeta(TypeModelDiffination owner,Method target) {
		PropertyModelMetaDataObject metaInfo = new PropertyModelMetaDataObject(owner, target);
		metaInfo.setId(target.getName());
		metaInfo.setName(target.getName());
		metaInfo.setAccess(Access.PRIVATE);
		metaInfo.setRequired(false);
		return metaInfo;
	}

	public static PropertyModelMetaData buildFieldMeta(TypeModelDiffination owner, Field target) {
		RelationPropertyModelMetaDataObject metaInfo = new RelationPropertyModelMetaDataObject(owner, target);
		metaInfo.setId(target.getName());
		metaInfo.setName(target.getName());
		metaInfo.setAccess(Access.valueOf(target.getModifiers()));
		metaInfo.setRequired(false);
		return metaInfo;
	}

	public static PropertyModelMetaData buildFieldMeta(TypeModelDiffination owner, AccessibleObject target, Property property) {
		if (target instanceof Field) {
			return buildFieldMeta(owner, (Field) target, property);
		} else {
			return buildFieldMeta(owner, (Method) target, property);
		}
	}
	
	public static PropertyModelMetaData buildFieldMeta(TypeModelDiffination owner, Field target,Property property) {
		PropertyModelMetaDataObject metaInfo = new PropertyModelMetaDataObject(owner, target);
		String id = property.id() == null || property.id().equals(Constants.DEFAULT)? target.getName() : property.id();
		metaInfo.setId(id);
		metaInfo.setName(target.getName());
		metaInfo.setAccess(property.access());
		metaInfo.setValue(property.value());
		metaInfo.setRequired(property.required());
		return metaInfo;
	}
	
	public static PropertyModelMetaData buildFieldMeta(TypeModelDiffination owner, Method target,Property property) {
		PropertyModelMetaDataObject metaInfo = new PropertyModelMetaDataObject(owner, target);
		String id = property.id() == null || property.id().equals(Constants.DEFAULT)? target.getName() : property.id();
		metaInfo.setId(id);
		metaInfo.setName(target.getName());
		metaInfo.setAccess(property.access());
		metaInfo.setValue(property.value());
		metaInfo.setRequired(property.required());
		return metaInfo;
	}

	public static PropertyModelMetaData buildFieldMeta(TypeModelDiffination owner, AccessibleObject target, PropertyModelResource<?> metaSetup) {
		if (target instanceof Field) {
			return buildFieldMeta(owner, (Field) target, metaSetup);
		} else {
			return buildFieldMeta(owner, (Method) target, metaSetup);
		}
	}
	
	public static PropertyModelMetaData buildFieldMeta(TypeModelDiffination owner, Method target,PropertyModelResource<?> property) {
		PropertyModelMetaDataObject metaInfo = new PropertyModelMetaDataObject(owner, target);
		String id = property.getId() == null || property.getId().equals(Constants.DEFAULT)? target.getName() : property.getId();
		metaInfo.setId(id);
		metaInfo.setName(target.getName());
		metaInfo.setAccess(Access.valueOf(property.getAccess()));
		metaInfo.setValue(property.getValue());
		metaInfo.setRequired(property.isRequired());
		return metaInfo;
	}
	
	public static PropertyModelMetaData buildFieldMeta(TypeModelDiffination owner, Field target,PropertyModelResource<?> property) {
		PropertyModelMetaDataObject metaInfo = new PropertyModelMetaDataObject(owner, target);
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
	
	public static RelationPropertyModelMetaData buildFieldMeta(TypeModelDiffination owner, AccessibleObject target,RelationPropertyModelResource<?> metaSetup) {
		if (target instanceof Field) {
			return buildFieldMeta(owner, (Field) target, metaSetup);
		} else {
			return buildFieldMeta(owner, (Method) target, metaSetup);
		}
	}
	
	public static RelationPropertyModelMetaData buildFieldMeta(TypeModelDiffination owner, Field target,RelationPropertyModelResource<?> property) {
		RelationPropertyModelMetaDataObject metaInfo = new RelationPropertyModelMetaDataObject(owner, target);
		String id = property.getId() == null || property.getId().equals(Constants.DEFAULT)? target.getName() : property.getId();
		metaInfo.setId(id);
		metaInfo.setName(target.getName());
		metaInfo.setAccess(Access.valueOf(property.getAccess()));
		metaInfo.setValue(property.getValue());
		metaInfo.setRequired(property.isRequired());
		return metaInfo;
	}
	

	public static RelationPropertyModelMetaData buildFieldMeta(TypeModelDiffination owner, Method target,RelationPropertyModelResource<?> property) {
		String id = property.getId() == null || property.getId().equals(Constants.DEFAULT)? target.getName() : property.getId();
		RelationPropertyModelMetaDataObject metaInfo = new RelationPropertyModelMetaDataObject(owner, target);
		metaInfo.setId(id);
		metaInfo.setName(target.getName());
		metaInfo.setAccess(Access.valueOf(property.getAccess()));
		metaInfo.setValue(property.getValue());
		metaInfo.setRequired(property.isRequired());
		return metaInfo;
	}
	
	
	public static RelationPropertyModelMetaData buildFieldMeta(TypeModelDiffination owner, AccessibleObject target, Relation property) {
		if (target instanceof Field) {
			return buildFieldMeta(owner, (Field) target, property);
		} else {
			return buildFieldMeta(owner, (Method) target, property);
		}
	}
	
	public static RelationPropertyModelMetaData buildFieldMeta(TypeModelDiffination owner, Field target,Relation property) {
		RelationPropertyModelMetaDataObject metaInfo = new RelationPropertyModelMetaDataObject(owner, target);
		String id = property.id() == null || property.id().equals(Constants.DEFAULT)? target.getName() : property.id();
		metaInfo.setId(id);
		metaInfo.setName(target.getName());
		metaInfo.setAccess(property.access());
		metaInfo.setRequired(property.required());
		return metaInfo;
	}
	
	public static RelationPropertyModelMetaDataObject buildFieldMeta(TypeModelDiffination owner, Method target,Relation property) {
		RelationPropertyModelMetaDataObject metaInfo = new RelationPropertyModelMetaDataObject(owner, target);
		String id = property.id() == null || property.id().equals(Constants.DEFAULT)? target.getName() : property.id();
		metaInfo.setId(id);
		metaInfo.setName(target.getName());
		metaInfo.setAccess(property.access());
		metaInfo.setRequired(property.required());
		return metaInfo;
	}
	
	
}
