package org.brijframework.meta.factories;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.brijframework.meta.KeyInfo;
import org.brijframework.meta.reflect.FieldMeta;

public interface FieldMetaFactory<T extends FieldMeta> extends MetaFactory<FieldMeta>{

	List<T> getFieldMetaInfo(String parentID);
	
	T FieldMetaInfo(String targetId);
	
	T getFieldMetaInfo(String perantId, String targetId);
	
	void register(T meta);
	
	ConcurrentHashMap<KeyInfo, ? extends FieldMeta> getCache();
}
