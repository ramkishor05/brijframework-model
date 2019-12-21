package org.brijframework.model.factories.deffination.impl;

import org.brijframework.container.Container;
import org.brijframework.model.diffination.ModelTypeDeffination;
import org.brijframework.model.factories.deffination.asm.AbstractTypeModelDeffinationFactory;
import org.brijframework.support.factories.SingletonFactory;
import org.brijframework.support.ordering.OrderOn;

@OrderOn(3)
public final class DefaultTypeModelDeffinationFactory extends AbstractTypeModelDeffinationFactory<String,ModelTypeDeffination> {
	
	protected DefaultTypeModelDeffinationFactory() {
	}

	protected static DefaultTypeModelDeffinationFactory factory;

	@SingletonFactory
	public static DefaultTypeModelDeffinationFactory getFactory() {
		if (factory == null) {
			factory = new DefaultTypeModelDeffinationFactory();
		}
		return factory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public DefaultTypeModelDeffinationFactory loadFactory() {
		Container container = getContainer();
		if(container==null) {
			return this;
		}
		container.getCache().forEach((groupkey, group)->{
			group.getCache().forEach((key,metadata)->{
				this.getCache().put((String)key, (ModelTypeDeffination)metadata);
			});
		});
		return this;
	}

	@Override
	public DefaultTypeModelDeffinationFactory clear() {
		if (getCache() != null) {
			getCache().clear();
		}
		return this;
	}

}