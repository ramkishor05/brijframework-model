package org.brijframework.model.factories.metadata.impl;

import org.brijframework.container.Container;
import org.brijframework.model.factories.metadata.asm.AbstractTypeModelMetaDataFactory;
import org.brijframework.model.metadata.TypeModelMetaData;
import org.brijframework.support.config.OrderOn;
import org.brijframework.support.config.SingletonFactory;

@OrderOn(3)
public final class TypeModelMetaDataFactoryImpl extends AbstractTypeModelMetaDataFactory<String,TypeModelMetaData> {
	
	protected TypeModelMetaDataFactoryImpl() {
	}

	protected static TypeModelMetaDataFactoryImpl factory;

	@SingletonFactory
	public static TypeModelMetaDataFactoryImpl getFactory() {
		if (factory == null) {
			factory = new TypeModelMetaDataFactoryImpl();
		}
		return factory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TypeModelMetaDataFactoryImpl loadFactory() {
		Container container = getContainer();
		if(container==null) {
			return this;
		}
		container.getCache().forEach((groupkey, group)->{
			group.getCache().forEach((key,metadata)->{
				this.getCache().put((String)key, (TypeModelMetaData)metadata);
			});
		});
		return this;
	}

	@Override
	public TypeModelMetaDataFactoryImpl clear() {
		if (getCache() != null) {
			getCache().clear();
		}
		return this;
	}

}