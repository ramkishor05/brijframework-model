package org.brijframework.model.factories;

import java.util.concurrent.ConcurrentHashMap;

import org.brijframework.factories.module.ModuleFactory;

public interface MetaFactory<K, T> extends ModuleFactory<K, T>{

	@Override
	default MetaFactory<K, T> clear() {
		this.getCache().clear();
		return this;
	}
	
	ConcurrentHashMap<K, T> getCache();

}
