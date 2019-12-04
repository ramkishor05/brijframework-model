package org.brijframework.model.factories.resource.annotation;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.brijframework.model.factories.resource.asm.AbstractTypeModelResourceFactory;
import org.brijframework.model.resource.TypeModelResource;
import org.brijframework.model.resource.impl.TypeModelResourceImpl;
import org.brijframework.model.resource.impl.PropertyModelResourceObject;
import org.brijframework.model.resource.impl.RelationPropertyModelResourceObject;
import org.brijframework.support.config.OrderOn;
import org.brijframework.support.config.SingletonFactory;
import org.brijframework.support.model.Model;
import org.brijframework.support.model.Models;
import org.brijframework.support.model.Property;
import org.brijframework.support.model.Relation;
import org.brijframework.util.accessor.PropertyAccessorUtil;
import org.brijframework.util.reflect.AnnotationUtil;
import org.brijframework.util.reflect.FieldUtil;
import org.brijframework.util.reflect.InstanceUtil;
import org.brijframework.util.reflect.ReflectionUtils;
import org.brijframework.util.support.Access;
import org.brijframework.util.support.Constants;

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
		ReflectionUtils.getInternalClassList().forEach(target -> {
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
		Map<String,Object> properties=new HashMap<String, Object>();
		TypeModelResourceImpl metaSetup=InstanceUtil.getInstance(TypeModelResourceImpl.class);
		properties.putAll(AnnotationUtil.getAnnotationData(model));
		PropertyAccessorUtil.setProperties(metaSetup, properties,Access.PRIVATE);
		for(Field field: FieldUtil.getAllField(target,Access.PRIVATE)) {
			if(field.isAnnotationPresent(Property.class)) {
				Map<String, Object> map=AnnotationUtil.getAnnotationData(field.getAnnotation(Property.class));
				metaSetup.getProperties().put(field.getName(), InstanceUtil.getInstance(PropertyModelResourceObject.class,map));
			}
			if(field.isAnnotationPresent(Relation.class)) {
				Map<String, Object> map=AnnotationUtil.getAnnotationData(field.getAnnotation(Relation.class));
				metaSetup.getProperties().put(field.getName(), InstanceUtil.getInstance(RelationPropertyModelResourceObject.class,map));
			}
		}
		if(metaSetup.getId()==null || Constants.DEFAULT.equals(metaSetup.getId())) {
			metaSetup.setId(target.getSimpleName());
		}
		metaSetup.setType(target.getName());
		metaSetup.setName(target.getSimpleName());
		this.register((TypeModelResource)metaSetup);
	}
}
