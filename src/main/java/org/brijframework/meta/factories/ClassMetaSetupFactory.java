package org.brijframework.meta.factories;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.brijframework.meta.MetaInfo;
import org.brijframework.meta.setup.ClassMetaSetup;

public interface ClassMetaSetupFactory extends MetaFactory<MetaInfo<?>>{

	List<ClassMetaSetup> getClassSetupList(Class<?> cls);

	List<ClassMetaSetup> getClassSetupList(Class<?> target, String parentID);

	ClassMetaSetup getClassSetup(String parentID);
	
	ConcurrentHashMap<String, MetaInfo<?>> getCache();
}
