package org.brijframework.meta.factories;

import java.lang.reflect.Type;
import java.util.Set;

import org.brijframework.meta.reflect.MethodMeta;

public interface MethodMetaInfoFactory extends MetaFactory<MethodMeta>{

	MethodMeta getMethodInfo(String parentID, String key);

	MethodMeta getMethodInfo(String parentID, String key, Type... types);

	Set<MethodMeta> getMethodInfoList(String parentID, String key);

	Set<MethodMeta> getMethodInfoList(String mdlID);

}
