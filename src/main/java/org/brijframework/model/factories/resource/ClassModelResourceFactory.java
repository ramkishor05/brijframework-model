package org.brijframework.model.factories.resource;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.brijframework.model.factories.MetaFactory;
import org.brijframework.model.resource.ClassModelResource;

public interface ClassModelResourceFactory<K,T> extends MetaFactory<String,ClassModelResource>{

	List<ClassModelResource> getClassSetupList(Class<?> cls);

	List<ClassModelResource> getClassSetupList(Class<?> target, String parentID);

	ClassModelResource getClassSetup(String parentID);
	
	ConcurrentHashMap<String, ClassModelResource> getCache();
}
