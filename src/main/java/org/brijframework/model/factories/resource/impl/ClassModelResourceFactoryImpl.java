package org.brijframework.model.factories.resource.impl;

import java.util.concurrent.ConcurrentHashMap;

import org.brijframework.group.Group;
import org.brijframework.model.factories.resource.asm.AbstractClassModelResourceFactory;
import org.brijframework.model.resource.ClassModelResource;
import org.brijframework.support.config.OrderOn;
import org.brijframework.support.config.SingletonFactory;

@OrderOn(3)
public final class ClassModelResourceFactoryImpl extends AbstractClassModelResourceFactory{

	protected static ClassModelResourceFactoryImpl factory;

	@SingletonFactory
	public static ClassModelResourceFactoryImpl getFactory() {
		if (factory == null) {
			factory = new ClassModelResourceFactoryImpl();
		}
		return factory;
	}

	
	@Override
	public ClassModelResourceFactoryImpl loadFactory() {
		return this;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ConcurrentHashMap<String, ClassModelResource> getCache() {
		for(Group group:getContainer().getCache().values()) {
			group.getCache().forEach((key,value)->{
				super.getCache().put((String)key, (ClassModelResource)value);
			});
		}
		return super.getCache();
	}

}