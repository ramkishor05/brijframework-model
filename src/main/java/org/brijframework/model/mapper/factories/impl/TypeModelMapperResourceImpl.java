package org.brijframework.model.mapper.factories.impl;

import org.brijframework.factories.impl.module.AbstractModuleFactory;
import org.brijframework.model.mapper.factories.ModelMapperFactory;
import org.brijframework.model.mapper.model.TypeModelMapperResource;
import org.brijframework.support.factories.SingletonFactory;

public class TypeModelMapperResourceImpl extends AbstractModuleFactory<String,TypeModelMapperResource> implements ModelMapperFactory<String,TypeModelMapperResource> {

	protected TypeModelMapperResourceImpl() {
	}

	protected static TypeModelMapperResourceImpl factory;

	@SingletonFactory
	public static TypeModelMapperResourceImpl getFactory() {
		if (factory == null) {
			factory = new TypeModelMapperResourceImpl();
		}
		return factory;
	}

	@Override
	public TypeModelMapperResourceImpl loadFactory() {
		return this;
	}

	public TypeModelMapperResource load(Class<?> target) {
		TypeModelMapperResource model=find(target.getSimpleName());
		if(model==null) {
			model = new TypeModelMapperResource();
			model.setId(target.getSimpleName());
			model.setType(target);
			register(target.getSimpleName(),model);
		}
		return model;
	}

	@Override
	protected void preregister(String key, TypeModelMapperResource value) {
	}

	@Override
	protected void postregister(String key, TypeModelMapperResource value) {
	}

}
