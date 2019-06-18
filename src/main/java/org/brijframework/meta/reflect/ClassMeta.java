package org.brijframework.meta.reflect;

import java.util.Map;

import org.brijframework.meta.MetaInfo;

public interface ClassMeta extends MetaInfo<Class<?>> {

	public String getType();

	public Class<?> getTarget();

	public ClassMeta getOwner();
	
	public FieldMeta getPropertyInfo(String _key);

	public ConstMeta getConstructor();

	public Map<String, FieldGroup> getProperties();

}
