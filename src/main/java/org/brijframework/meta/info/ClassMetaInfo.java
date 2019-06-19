package org.brijframework.meta.info;

import java.util.Map;

import org.brijframework.meta.MetaInfo;

public interface ClassMetaInfo extends MetaInfo<Class<?>> {

	public String getType();

	public Class<?> getTarget();

	public ClassMetaInfo getOwner();
	
	public FieldMetaInfo getPropertyInfo(String _key);

	public ConstMetaInfo getConstructor();

	public Map<String, FieldGroup> getProperties();

}
