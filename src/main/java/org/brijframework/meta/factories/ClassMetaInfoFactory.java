package org.brijframework.meta.factories;

import java.util.List;

import org.brijframework.meta.reflect.ClassMetaInfo;

public interface ClassMetaInfoFactory extends MetaInfoFactory<ClassMetaInfo>{

	List<ClassMetaInfo> getClassInfoList(Class<?> cls);

	List<ClassMetaInfo> getClassInfoList(Class<?> target, String parentID);

	ClassMetaInfo getClassInfo(String parentID);
}
