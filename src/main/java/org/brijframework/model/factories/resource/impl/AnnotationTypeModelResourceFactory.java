package org.brijframework.model.factories.resource.impl;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Map.Entry;

import org.brijframework.model.factories.resource.asm.AbstractTypeModelResourceFactory;
import org.brijframework.model.resource.TypeModelResource;
import org.brijframework.model.resource.impl.PropertyModelResourceImpl;
import org.brijframework.model.resource.impl.RelationPropertyModelResourceImpl;
import org.brijframework.model.resource.impl.TypeModelResourceImpl;
import org.brijframework.support.factories.SingletonFactory;
import org.brijframework.support.model.Model;
import org.brijframework.support.model.Models;
import org.brijframework.support.model.properties.Property;
import org.brijframework.support.model.properties.Relation;
import org.brijframework.support.ordering.OrderOn;
import org.brijframework.util.factories.ReflectionFactory;
import org.brijframework.util.reflect.FieldUtil;
import org.brijframework.util.reflect.InstanceUtil;
import org.brijframework.util.support.Access;
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
		TypeModelResourceImpl metaSetup=InstanceUtil.getInstance(TypeModelResourceImpl.class);
		metaSetup.setId(StringUtil.isEmpty(model.id())|| Constants.DEFAULT.equalsIgnoreCase(model.id())?target.getSimpleName(): model.id());
		metaSetup.setType(target.getName());
		metaSetup.setName(StringUtil.isEmpty(model.name())|| Constants.DEFAULT.equalsIgnoreCase(model.name())?target.getSimpleName(): model.name());
		metaSetup.setAccess(model.access()!=null ? model.access().toString(): Access.PUBLIC.toString());
		Map<String, Field> fieldMap = FieldUtil.getAllFieldMap(target,Access.PRIVATE);
		if(model.properties() !=null) {
			for(Property property: model.properties()) {
				Field field = fieldMap.get(property.name());
				if(field==null) {
					continue;
				}
				PropertyModelResourceImpl propertyModelResource = getPropertyModelResource(target, field, property);
				metaSetup.getProperties().put(propertyModelResource.getId(),propertyModelResource );
			}
		}
		if(model.relations() !=null) {
			for(Relation property: model.relations()) {
				Field field = fieldMap.get(property.name());
				if(field==null) {
					continue;
				}
				RelationPropertyModelResourceImpl propertyModelResource = getPropertyModelResource(target, field, property);
				metaSetup.getProperties().put(propertyModelResource.getId(),propertyModelResource );
			}
		}
		for(Entry<String, Field> entry: fieldMap.entrySet()) {
			String id=entry.getKey();
			Field field = entry.getValue();
			if(field.isAnnotationPresent(Property.class)) {
				metaSetup.getProperties().put(id, getPropertyModelResource(target, field, field.getAnnotation(Property.class)));
			}
			if(field.isAnnotationPresent(Relation.class)) {
				metaSetup.getProperties().put(id, getPropertyModelResource(target, field, field.getAnnotation(Relation.class)));
			}
		}
		this.register((TypeModelResource)metaSetup);
	}
	
	private PropertyModelResourceImpl getPropertyModelResource(Class<?> target,Field field,Property property) {
		PropertyModelResourceImpl propertyResource=new PropertyModelResourceImpl();
		propertyResource.setId(StringUtil.isEmpty(property.id())|| Constants.DEFAULT.equalsIgnoreCase(property.id())?field.getName(): property.id());
		propertyResource.setType(field.getType().getName());
		propertyResource.setName(StringUtil.isEmpty(property.name())|| Constants.DEFAULT.equalsIgnoreCase(property.name())?field.getName(): property.name());
		propertyResource.setAccess(property.access()!=null ? property.access().toString(): Access.PUBLIC.toString());
		propertyResource.setValue(StringUtil.isEmpty(property.value())|| Constants.DEFAULT.equalsIgnoreCase(property.value())? null: property.value());
		propertyResource.setRequired(property.required());
		return propertyResource;
	}
	
	private RelationPropertyModelResourceImpl getPropertyModelResource(Class<?> target,Field field,Relation property) {
		RelationPropertyModelResourceImpl propertyResource=new RelationPropertyModelResourceImpl();
		propertyResource.setId(StringUtil.isEmpty(property.id())|| Constants.DEFAULT.equalsIgnoreCase(property.id())?field.getName(): property.id());
		propertyResource.setType(field.getType().getName());
		propertyResource.setName(StringUtil.isEmpty(property.name())|| Constants.DEFAULT.equalsIgnoreCase(property.name())?field.getName(): property.name());
		propertyResource.setAccess(property.access()!=null ? property.access().toString(): Access.PUBLIC.toString());
		propertyResource.setValue(StringUtil.isEmpty(property.value())|| Constants.DEFAULT.equalsIgnoreCase(property.value())? null: property.value());
		propertyResource.setRequired(property.required());
		propertyResource.setMappedBy(property.mappedBy());
		return propertyResource;
	}
}
