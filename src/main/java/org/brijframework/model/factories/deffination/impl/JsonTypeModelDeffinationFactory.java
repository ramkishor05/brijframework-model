package org.brijframework.model.factories.deffination.impl;

import org.brijframework.model.diffination.ModelTypeDeffination;
import org.brijframework.model.factories.deffination.asm.AbstractTypeModelDeffinationFactory;
import org.brijframework.model.factories.resource.impl.JsonTypeModelResourceFactory;
import org.brijframework.support.factories.SingletonFactory;
import org.brijframework.support.ordering.OrderOn;

@OrderOn(2)
public class JsonTypeModelDeffinationFactory extends AbstractTypeModelDeffinationFactory<String,ModelTypeDeffination> {

	protected JsonTypeModelDeffinationFactory() {
	}

	protected static JsonTypeModelDeffinationFactory factory;

	@SingletonFactory
	public static JsonTypeModelDeffinationFactory getFactory() {
		if (factory == null) {
			factory = new JsonTypeModelDeffinationFactory();
		}
		return factory;
	}

	@Override
	public JsonTypeModelDeffinationFactory loadFactory() {
		JsonTypeModelResourceFactory.getFactory().getCache().forEach((key,modelResource)->{
			register(key, modelResource);
		});
		return this;
	}

	@Override
	public JsonTypeModelDeffinationFactory clear() {
		if (getCache() != null) {
			getCache().clear();
		}
		return this;
	}
}
