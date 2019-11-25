package org.brijframework.model.factories.metadata;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.brijframework.model.factories.MetaFactory;
import org.brijframework.model.info.ClassModelMetaData;

public interface ClassMetaDataFactory extends MetaFactory<String, ClassModelMetaData>{

	List<ClassModelMetaData> getClassInfoList(Class<?> cls);

	List<ClassModelMetaData> getClassInfoList(Class<?> target, String parentID);

	ClassModelMetaData getClassInfo(String parentID);
	
	ConcurrentHashMap<String, ClassModelMetaData> getCache();
}
