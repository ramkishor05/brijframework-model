package org.brijframework.model.factories.resource.annotation;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.brijframework.model.factories.resource.asm.impl.ClassModelResourceFactoryImpl;
import org.brijframework.model.resource.ClassModelResource;
import org.brijframework.model.setup.impl.ClassModelResourceObject;
import org.brijframework.model.setup.impl.PropertyModelResourceObject;
import org.brijframework.model.setup.impl.RelationPropertyModelResourceObject;
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

public class AnnotationClassModelResourceFactory extends ClassModelResourceFactoryImpl{
	
	private AnnotationClassModelResourceFactory() {
	}
	
	private static AnnotationClassModelResourceFactory factory;

	@Assignable
	public static AnnotationClassModelResourceFactory getFactory() {
		if (factory == null) {
			factory = new AnnotationClassModelResourceFactory();
		}
		return factory;
	}

	@Override
	public AnnotationClassModelResourceFactory loadFactory() {
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
		ClassModelResourceObject metaSetup=InstanceUtil.getInstance(ClassModelResourceObject.class);
		properties.putAll(AnnotationUtil.getAnnotationData(model));
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
		PropertyAccessorUtil.setProperties(metaSetup, properties,Access.PRIVATE);
		if(metaSetup.getId()==null || Constants.DEFAULT.equals(metaSetup.getId())) {
			metaSetup.setId(target.getSimpleName());
		}
		metaSetup.setTarget(target.getName());
		metaSetup.setName(target.getSimpleName());
		this.register((ClassModelResource)metaSetup);
	}
}
