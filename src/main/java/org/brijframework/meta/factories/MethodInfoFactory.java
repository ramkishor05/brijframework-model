package org.brijframework.meta.factories;

import java.lang.reflect.Type;
import java.util.Set;

import org.brijframework.meta.properties.MethodInfo;

public interface MethodInfoFactory extends MetaInfoFactory{

	MethodInfo getMethodInfo(String parentID, String key);

	MethodInfo getMethodInfo(String parentID, String key, Type... types);

	Set<MethodInfo> getMethodInfoList(String parentID, String key);

	Set<MethodInfo> getMethodInfoList(String mdlID);

}
