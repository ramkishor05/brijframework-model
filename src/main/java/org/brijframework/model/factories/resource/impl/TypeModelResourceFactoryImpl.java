package org.brijframework.model.factories.resource.impl;

import org.brijframework.container.Container;
import org.brijframework.group.Group;
import org.brijframework.model.factories.resource.asm.AbstractTypeModelResourceFactory;
import org.brijframework.model.resource.TypeModelResource;
import org.brijframework.support.config.OrderOn;
import org.brijframework.support.config.SingletonFactory;

@OrderOn(3)
public final class TypeModelResourceFactoryImpl extends AbstractTypeModelResourceFactory<String, TypeModelResource>{

	protected static TypeModelResourceFactoryImpl factory;

	@SingletonFactory
	public static TypeModelResourceFactoryImpl getFactory() {
		if (factory == null) {
			factory = new TypeModelResourceFactoryImpl();
		}
		return factory;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public TypeModelResourceFactoryImpl loadFactory() {
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