package org.brijframework.model.factories.resource.impl;

import org.brijframework.container.Container;
import org.brijframework.group.Group;
import org.brijframework.model.factories.resource.asm.AbstractTypeModelResourceFactory;
import org.brijframework.model.resource.TypeModelResource;
import org.brijframework.support.factories.SingletonFactory;
import org.brijframework.support.ordering.OrderOn;

@OrderOn(3)
public final class DefaultTypeModelResourceFactory extends AbstractTypeModelResourceFactory<String, TypeModelResource>{

	protected static DefaultTypeModelResourceFactory factory;

	@SingletonFactory
	public static DefaultTypeModelResourceFactory getFactory() {
		if (factory == null) {
			factory = new DefaultTypeModelResourceFactory();
		}
		return factory;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public DefaultTypeModelResourceFactory loadFactory() {
		Container container= getContainer();
		if(container==null) {
			return this;
		}
		for(Group group:getContainer().getCache().values()) {
			group.getCache().forEach((key,value)->{
				super.getCache().put((String)key, (TypeModelResource)value);
			});
		}
		return this;
	}

}