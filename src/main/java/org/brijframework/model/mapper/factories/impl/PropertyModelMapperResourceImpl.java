package org.brijframework.model.mapper.factories.impl;

import org.brijframework.factories.impl.module.AbstractModuleFactory;
import org.brijframework.model.mapper.factories.ModelMapperFactory;
import org.brijframework.model.mapper.model.PropertyModelMapperResource;
import org.brijframework.support.config.SingletonFactory;

public class PropertyModelMapperResourceImpl extends AbstractModuleFactory<String,PropertyModelMapperResource> implements ModelMapperFactory<String,PropertyModelMapperResource> {

	protected PropertyModelMapperResourceImpl() {
	}

	protected static PropertyModelMapperResourceImpl factory;

	@SingletonFactory
	public static PropertyModelMapperResourceImpl getFactory() {
		if (factory == null) {
			factory = new PropertyModelMapperResourceImpl();
		}
		return factory;
	}

	@Override
	public PropertyModelMapperResourceImpl loadFactory() {
		return this;
	}

	public PropertyModelMapperResource getMetaInfo(String id) {
		return null;
	}

	@Override
	protected void preregister(String key, PropertyModelMapperResource value) {
	}

	@Override
	protected void postregister(String key, PropertyModelMapperResource value) {
	}

}
