package org.brijframework.model.factories.resource.impl;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Map.Entry;

import org.brijframework.Access;
import org.brijframework.model.factories.resource.asm.AbstractTypeModelResourceFactory;
import org.brijframework.model.resource.ConstructorModelResource;
import org.brijframework.model.resource.ParameterModelResource;
import org.brijframework.model.resource.TypeModelResource;
import org.brijframework.model.resource.impl.ConstructorModelResourceImpl;
import org.brijframework.model.resource.impl.ParameterModelResourceImpl;
import org.brijframework.model.resource.impl.PropertyModelResourceImpl;
import org.brijframework.model.resource.impl.RelationPropertyModelResourceImpl;
import org.brijframework.model.resource.impl.TypeModelResourceImpl;
import org.brijframework.support.factories.SingletonFactory;
import org.brijframework.support.model.Model;
import org.brijframework.support.model.ModelConstruct;
import org.brijframework.support.model.ModelParam;
import org.brijframework.support.model.Models;
import org.brijframework.support.model.properties.ModelProperty;
import org.brijframework.support.model.properties.ModelRelation;
import org.brijframework.support.ordering.OrderOn;
import org.brijframework.util.asserts.Assertion;
import org.brijframework.util.factories.ReflectionFactory;
import org.brijframework.util.reflect.FieldUtil;
import org.brijframework.util.reflect.InstanceUtil;
import org.brijframework.util.support.ReflectionAccess;
import org.brijframework.util.support.Constants;
import org.brijframework.util.text.StringUtil;

@OrderOn(1)
public class AnnotationTypeModelResourceFactory extends AbstractTypeModelResourceFactory<String, TypeModelResource>{
	
	private AnnotationTypeModelResourceFactory() {
	}
	
	private static AnnotationTypeModelResourceFactory factory;

	@SingletonFactory
	public static AnnotationTypeModelResourceFactory getFactory() {
		if (factory == null) {
			factory = new AnnotationTypeModelResourceFactory();
		}
		return factory;
	}

	@Override
	public AnnotationTypeModelResourceFactory loadFactory() {
		ReflectionFactory.getFactory().getInternalClassList().forEach(target -> {
			if (target.isAnnotationPresent(Models.class) || target.isAnnotationPresent(Model.class)) {
				this.register(target);
			}
		});
		return this;
	}

	public void register(Class<?> target) {
		if (target.isAnnotationPresent(Models.class)) {
			Models models = target.getAnnotation(Models.class);
			for (Model metaSetup : models.value()) {
				this.register(target, metaSetup);
			}
		}
		if (target.isAnnotationPresent(Model.class)) {
			Model metaSetup = target.getAnnotation(Model.class);
			this.register(target, metaSetup);
		}
	}

	public void register(Class<?> target, Model model) {
		TypeModelResourceImpl typeModelResource=InstanceUtil.getInstance(TypeModelResourceImpl.class);
		typeModelResource.setId(StringUtil.isEmpty(model.id())|| Constants.DEFAULT.equalsIgnoreCase(model.id())?target.getSimpleName(): model.id());
		typeModelResource.setType(target.getName());
		typeModelResource.setName(StringUtil.isEmpty(model.name())|| Constants.DEFAULT.equalsIgnoreCase(model.name())?target.getSimpleName(): model.name());
		typeModelResource.setAccess(model.access()!=null ? model.access().toString(): ReflectionAccess.PUBLIC.toString());
		typeModelResource.setConstructor(createConstructor(typeModelResource,model.constructor()));
		Map<String, Field> fieldMap = FieldUtil.getAllFieldMap(target,ReflectionAccess.PRIVATE);
		if(model.properties() !=null) {
			for(ModelProperty property: model.properties()) {
				Field field = fieldMap.get(property.name());
				if(field==null) {
					continue;
				}
				PropertyModelResourceImpl propertyModelResource = getPropertyModelResource(target, field, property);
				typeModelResource.getProperties().put(propertyModelResource.getId(),propertyModelResource );
			}
		}
		if(model.relations() !=null) {
			for(ModelRelation property: model.relations()) {
				Field field = fieldMap.get(property.name());
				if(field==null) {
					continue;
				}
				RelationPropertyModelResourceImpl propertyModelResource = getPropertyModelResource(target, field, property);
				typeModelResource.getProperties().put(propertyModelResource.getId(),propertyModelResource );
			}
		}
		for(Entry<String, Field> entry: fieldMap.entrySet()) {
			String id=entry.getKey();
			Field field = entry.getValue();
			if(field.isAnnotationPresent(ModelProperty.class)) {
				typeModelResource.getProperties().put(id, getPropertyModelResource(target, field, field.getAnnotation(ModelProperty.class)));
			}
			if(field.isAnnotationPresent(ModelRelation.class)) {
				typeModelResource.getProperties().put(id, getPropertyModelResource(target, field, field.getAnnotation(ModelRelation.class)));
			}
		}
		this.register(typeModelResource);
	}
	
	private ConstructorModelResource<?> createConstructor(TypeModelResource typeModelResource,ModelConstruct constructor) {
		ConstructorModelResourceImpl constructorModelResource = new ConstructorModelResourceImpl();
		constructorModelResource.setAccess(constructor.access().toString());
		constructorModelResource.setId(StringUtil.isEmpty(constructor.id())|| Constants.DEFAULT.equalsIgnoreCase(constructor.id())? typeModelResource.getId(): constructor.id());
		constructorModelResource.setName(StringUtil.isEmpty(constructor.name())|| Constants.DEFAULT.equalsIgnoreCase(constructor.name())? typeModelResource.getName(): constructor.name());
		if(constructor.params()!=null)
		for(ModelParam modelParam: constructor.params()) {
			constructorModelResource.getParameterList().add(getModelParam(modelParam));
		}
		return constructorModelResource;
	}

	private ParameterModelResource getModelParam(ModelParam modelParam) {
		ParameterModelResourceImpl parameterModelResource=new ParameterModelResourceImpl();
		parameterModelResource.setId(modelParam.name());
		parameterModelResource.setIndex(modelParam.index());
		Assertion.notNull(modelParam.type(), "Parameter type is required.");
		parameterModelResource.setType(modelParam.type().getName());
		parameterModelResource.setAccess(Access.AUTO.toString());
		return parameterModelResource;
	}

	private PropertyModelResourceImpl getPropertyModelResource(Class<?> target,Field field,ModelProperty property) {
		PropertyModelResourceImpl propertyResource=new PropertyModelResourceImpl();
		propertyResource.setId(StringUtil.isEmpty(property.id())|| Constants.DEFAULT.equalsIgnoreCase(property.id())?field.getName(): property.id());
		propertyResource.setType(field.getType().getName());
		propertyResource.setName(StringUtil.isEmpty(property.name())|| Constants.DEFAULT.equalsIgnoreCase(property.name())?field.getName(): property.name());
		propertyResource.setAccess(property.access()!=null ? property.access().toString(): ReflectionAccess.PUBLIC.toString());
		propertyResource.setRequired(property.required());
		return propertyResource;
	}
	
	private RelationPropertyModelResourceImpl getPropertyModelResource(Class<?> target,Field field,ModelRelation property) {
		RelationPropertyModelResourceImpl propertyResource=new RelationPropertyModelResourceImpl();
		propertyResource.setId(StringUtil.isEmpty(property.id())|| Constants.DEFAULT.equalsIgnoreCase(property.id())?field.getName(): property.id());
		propertyResource.setType(field.getType().getName());
		propertyResource.setName(StringUtil.isEmpty(property.name())|| Constants.DEFAULT.equalsIgnoreCase(property.name())?field.getName(): property.name());
		propertyResource.setAccess(property.access()!=null ? property.access().toString(): ReflectionAccess.PUBLIC.toString());
		propertyResource.setRequired(property.required());
		propertyResource.setMappedBy(property.mappedBy());
		return propertyResource;
	}
}
