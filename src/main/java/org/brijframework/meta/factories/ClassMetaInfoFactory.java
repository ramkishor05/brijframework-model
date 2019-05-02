package org.brijframework.meta.factories;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.brijframework.meta.KeyInfo;
import org.brijframework.meta.reflect.ClassMetaInfo;

public interface ClassMetaInfoFactory extends MetaInfoFactory<ClassMetaInfo>{

	List<ClassMetaInfo> getClassInfoList(Class<?> cls);

	List<ClassMetaInfo> getClassInfoList(Class<?> target, String parentID);

	ClassMetaInfo getClassInfo(String parentID);
	
	ConcurrentHashMap<KeyInfo, ClassMetaInfo> getCache();
}
