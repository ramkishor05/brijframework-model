package org.brijframework.model.factories.annotation;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.brijframework.model.factories.asm.ClassMetaSetupFactoryImpl;
import org.brijframework.model.setup.impl.ModelMetaSetup;
import org.brijframework.model.setup.impl.PropertyMetaSetup;
import org.brijframework.model.setup.impl.RelPtpMetaSetup;
import org.brijframework.support.config.Assignable;
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

public class AnnotationMetaSetupFactory extends ClassMetaSetupFactoryImpl{
	
	private AnnotationMetaSetupFactory() {
	}
	
	private static AnnotationMetaSetupFactory factory;

	@Assignable
	public static AnnotationMetaSetupFactory getFactory() {
		if (factory == null) {
			factory = new AnnotationMetaSetupFactory();
		}
		return factory;
	}

	@Override
	public AnnotationMetaSetupFactory loadFactory() {
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
		ModelMetaSetup metaSetup=InstanceUtil.getInstance(ModelMetaSetup.class);
		properties.putAll(AnnotationUtil.getAnnotationData(model));
		for(Field field: FieldUtil.getAllField(target,Access.PRIVATE)) {
			if(field.isAnnotationPresent(Property.class)) {
				Map<String, Object> map=AnnotationUtil.getAnnotationData(field.getAnnotation(Property.class));
				metaSetup.getProperties().put(field.getName(), InstanceUtil.getInstance(PropertyMetaSetup.class,map));
			}
			if(field.isAnnotationPresent(Relation.class)) {
				Map<String, Object> map=AnnotationUtil.getAnnotationData(field.getAnnotation(Relation.class));
				metaSetup.getProperties().put(field.getName(), InstanceUtil.getInstance(RelPtpMetaSetup.class,map));
			}
		}
		PropertyAccessorUtil.setProperties(metaSetup, properties,Access.PRIVATE);
		if(metaSetup.getId()==null || Constants.DEFAULT.equals(metaSetup.getId())) {
			metaSetup.setId(target.getSimpleName());
		}
		metaSetup.setTarget(target.getName());
		metaSetup.setName(target.getSimpleName());
		this.register(metaSetup);
	}
}
