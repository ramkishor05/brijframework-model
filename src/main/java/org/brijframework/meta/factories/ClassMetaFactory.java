package org.brijframework.meta.factories;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.brijframework.meta.KeyInfo;
import org.brijframework.meta.reflect.ClassMeta;

public interface ClassMetaFactory extends MetaFactory<ClassMeta>{

	List<ClassMeta> getClassInfoList(Class<?> cls);

	List<ClassMeta> getClassInfoList(Class<?> target, String parentID);

	ClassMeta getClassInfo(String parentID);
	
	ConcurrentHashMap<KeyInfo, ClassMeta> getCache();
}
