package org.brijframework.meta.factories;

import java.lang.reflect.Type;
import java.util.Set;

import org.brijframework.meta.reflect.MethodMetaInfo;

public interface MethodMetaInfoFactory extends MetaInfoFactory<MethodMetaInfo>{

	MethodMetaInfo getMethodInfo(String parentID, String key);

	MethodMetaInfo getMethodInfo(String parentID, String key, Type... types);

	Set<MethodMetaInfo> getMethodInfoList(String parentID, String key);

	Set<MethodMetaInfo> getMethodInfoList(String mdlID);

}
