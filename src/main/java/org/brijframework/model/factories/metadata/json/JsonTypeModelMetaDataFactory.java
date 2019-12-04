package org.brijframework.model.factories.metadata.json;

import org.brijframework.model.factories.metadata.asm.AbstractTypeModelMetaDataFactory;
import org.brijframework.model.factories.resource.json.JsonTypeModelResourceFactory;
import org.brijframework.model.metadata.TypeModelMetaData;
import org.brijframework.support.config.OrderOn;
import org.brijframework.support.config.SingletonFactory;

@OrderOn(2)
public class JsonTypeModelMetaDataFactory extends AbstractTypeModelMetaDataFactory<String,TypeModelMetaData> {

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
