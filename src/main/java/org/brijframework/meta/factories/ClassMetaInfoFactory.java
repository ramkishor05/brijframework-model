package org.brijframework.meta.factories;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.brijframework.meta.info.ClassMetaInfo;

public interface ClassMetaInfoFactory extends MetaFactory<ClassMetaInfo>{

	List<ClassMetaInfo> getClassInfoList(Class<?> cls);

	List<ClassMetaInfo> getClassInfoList(Class<?> target, String parentID);

	ClassMetaInfo getClassInfo(String parentID);
	
	ConcurrentHashMap<String, ClassMetaInfo> getCache();
}
