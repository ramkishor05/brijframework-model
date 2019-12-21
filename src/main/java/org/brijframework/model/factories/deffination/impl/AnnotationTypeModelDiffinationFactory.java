package org.brijframework.model.factories.deffination.impl;

import org.brijframework.model.diffination.ModelTypeDeffination;
import org.brijframework.model.factories.deffination.asm.AbstractTypeModelDeffinationFactory;
import org.brijframework.model.factories.resource.impl.AnnotationTypeModelResourceFactory;
import org.brijframework.support.factories.SingletonFactory;
import org.brijframework.support.ordering.OrderOn;

@OrderOn(1)
public class AnnotationTypeModelDiffinationFactory extends AbstractTypeModelDeffinationFactory<String,ModelTypeDeffination> {

	protected AnnotationTypeModelDiffinationFactory() {
	}

	protected static AnnotationTypeModelDiffinationFactory factory;

	@SingletonFactory
	public static AnnotationTypeModelDiffinationFactory getFactory() {
		if (factory == null) {
			factory = new AnnotationTypeModelDiffinationFactory();
		}
		return factory;
	}

	@Override
	public AnnotationTypeModelDiffinationFactory loadFactory() {
		AnnotationTypeModelResourceFactory.getFactory().getCache().forEach((key,modelResource)->{
			register(key, modelResource);
		});
		return this;
	}

	@Override
	public AnnotationTypeModelDiffinationFactory clear() {
		if (getCache() != null) {
			getCache().clear();
		}
		return this;
	}
}
