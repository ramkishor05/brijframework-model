package org.brijframework.meta.factories;

import java.util.List;

import org.brijframework.meta.blueprint.ClassInfo;

public interface ClassInfoFactory extends MetaInfoFactory{

	List<ClassInfo> getClassInfoList(Class<?> cls);

	List<ClassInfo> getClassInfoList(Class<?> target, String parentID);

	ClassInfo getClassInfo(String parentID);
}
