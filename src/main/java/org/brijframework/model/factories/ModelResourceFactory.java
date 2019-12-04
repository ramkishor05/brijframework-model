package org.brijframework.model.factories;

import java.util.List;

import org.brijframework.factories.module.ModuleFactory;

public interface ModelResourceFactory<K, T> extends ModuleFactory<K, T>{

	List<T> findByType(String cls);
}
