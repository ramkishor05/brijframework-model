package org.brijframework.meta.factories;

import java.util.concurrent.ConcurrentHashMap;

import org.brijframework.factories.Factory;
import org.brijframework.meta.MetaInfo;

public interface MetaFactory<T extends MetaInfo<?>> extends Factory{

	@Override
	default MetaFactory<T> clear() {
		this.getCache().clear();
		return this;
	}
	
	ConcurrentHashMap<String, T> getCache();

}
