package org.brijframework.model.mapper.factories.impl;

import java.util.Map;

import org.brijframework.factories.impl.module.AbstractModuleFactory;
import org.brijframework.model.mapper.factories.ModelMapperFactory;
import org.brijframework.model.mapper.model.TypeModelMapperResource;
import org.brijframework.support.config.SingletonFactory;
import org.brijframework.support.mapper.Mapper;
import org.brijframework.support.mapper.Mappers;
import org.brijframework.util.accessor.PropertyAccessorUtil;
import org.brijframework.util.factories.ReflectionFactory;
import org.brijframework.util.reflect.AnnotationUtil;

public class AnnotationTypeModelMapperResourceFactory extends AbstractModuleFactory<String,TypeModelMapperResource> implements ModelMapperFactory<String,TypeModelMapperResource> {

	protected AnnotationTypeModelMapperResourceFactory() {
	}

	protected static AnnotationTypeModelMapperResourceFactory factory;

	@SingletonFactory
	public static AnnotationTypeModelMapperResourceFactory getFactory() {
		if (factory == null) {
			factory = new AnnotationTypeModelMapperResourceFactory();
		}
		return factory;
	}

	@Override
	public AnnotationTypeModelMapperResourceFactory loadFactory() {
		ReflectionFactory.getFactory().getInternalClassList().forEach(target -> {
			this.register(target);
		});
		return this;
	}

	public void register(Class<?> target) {
		if (target.isAnnotationPresent(Mappers.class)) {
			Mappers mappers=target.getAnnotation(Mappers.class);
			for(Mapper mapper:mappers.value()) {
				register(target, mapper);
			}
		} else if (target.isAnnotationPresent(Mapper.class)) {
			register(target, target.getAnnotation(Mapper.class));
		}
	}

	public TypeModelMapperResource register(Class<?> target, Mapper mapper) {
		Map<String, Object> properties = AnnotationUtil.getAnnotationData(mapper);
		TypeModelMapperResource owner = new TypeModelMapperResource();
		PropertyAccessorUtil.setProperties(owner, properties);
		owner.setId(target.getSimpleName());
		owner.setType(target);
		owner.setName(target.getSimpleName());
		register(owner.getId(),owner);
		return owner;
	}

	@Override
	protected void preregister(String key, TypeModelMapperResource value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void postregister(String key, TypeModelMapperResource value) {
		// TODO Auto-generated method stub
		
	}

	
}
