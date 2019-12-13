package org.brijframework.model.factories.metadata.json;

import org.brijframework.model.diffination.TypeModelDiffination;
import org.brijframework.model.factories.metadata.asm.AbstractTypeModelMetaDataFactory;
import org.brijframework.model.factories.resource.impl.JsonTypeModelResourceFactory;
import org.brijframework.support.factories.SingletonFactory;
import org.brijframework.support.ordering.OrderOn;

@OrderOn(2)
public class JsonTypeModelMetaDataFactory extends AbstractTypeModelMetaDataFactory<String,TypeModelDiffination> {

	protected JsonTypeModelMetaDataFactory() {
	}

	protected static JsonTypeModelMetaDataFactory factory;

	@SingletonFactory
	public static JsonTypeModelMetaDataFactory getFactory() {
		if (factory == null) {
			factory = new JsonTypeModelMetaDataFactory();
		}
		return factory;
	}

	@Override
	public JsonTypeModelMetaDataFactory loadFactory() {
		JsonTypeModelResourceFactory.getFactory().getCache().forEach((key,modelResource)->{
			register(key, modelResource);
		});
		return this;
	}

	@Override
	public JsonTypeModelMetaDataFactory clear() {
		if (getCache() != null) {
			getCache().clear();
		}
		return this;
	}
}
