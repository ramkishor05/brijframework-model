package org.brijframework.meta.properties;

import java.lang.reflect.AccessibleObject;
import java.util.Map;

import org.brijframework.meta.MetaInfo;
import org.brijframework.meta.blueprint.ClassInfo;

public interface FieldInfo extends MetaInfo{

	public String getParentID();
	
	public Object getValue();

	@SuppressWarnings("unchecked")
	public AccessibleObject getTarget();

	public void papulate(Map<String, Object> properties);

	public ClassInfo getPerantInfo();

	public boolean isField();

	public boolean isGetter();

	public boolean isSetter();
	
}
