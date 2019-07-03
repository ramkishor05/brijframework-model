package org.brijframework.model.factories;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.brijframework.model.setup.ClassMetaSetup;

public interface ClassMetaSetupFactory extends MetaFactory<ClassMetaSetup>{

	List<ClassMetaSetup> getClassSetupList(Class<?> cls);

	List<ClassMetaSetup> getClassSetupList(Class<?> target, String parentID);

	ClassMetaSetup getClassSetup(String parentID);
	
	ConcurrentHashMap<String, ClassMetaSetup> getCache();
}
