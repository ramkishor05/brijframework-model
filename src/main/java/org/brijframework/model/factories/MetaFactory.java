package org.brijframework.model.factories;

import java.util.concurrent.ConcurrentHashMap;

import org.brijframework.factories.Factory;

public interface MetaFactory<T> extends Factory{

	@Override
	default MetaFactory<T> clear() {
		this.getCache().clear();
		return this;
	}
	
	ConcurrentHashMap<String, T> getCache();

}
