package org.brijframework.model.factories.metadata.annotation;

import org.brijframework.model.factories.metadata.asm.AbstractTypeModelMetaDataFactory;
import org.brijframework.model.factories.resource.annotation.AnnotationTypeModelResourceFactory;
import org.brijframework.model.metadata.TypeModelMetaData;
import org.brijframework.support.config.OrderOn;
import org.brijframework.support.config.SingletonFactory;

@OrderOn(1)
public class AnnotationTypeModelMetaDataFactory extends AbstractTypeModelMetaDataFactory<String,TypeModelMetaData> {

	protected AnnotationTypeModelMetaDataFactory() {
	}

	protected static AnnotationTypeModelMetaDataFactory factory;

	@SingletonFactory
	public static AnnotationTypeModelMetaDataFactory getFactory() {
		if (factory == null) {
			factory = new AnnotationTypeModelMetaDataFactory();
		}
		return factory;
	}

	@Override
	public AnnotationTypeModelMetaDataFactory loadFactory() {
		AnnotationTypeModelResourceFactory.getFactory().getCache().forEach((key,modelResource)->{
			register(key, modelResource);
		});
		return this;
	}

	@Override
	public AnnotationTypeModelMetaDataFactory clear() {
		if (getCache() != null) {
			getCache().clear();
		}
		return this;
	}
}
