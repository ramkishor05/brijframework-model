package org.brijframework.meta.relations;

import org.brijframework.meta.blueprint.ClassInfo;
import org.brijframework.meta.properties.FieldInfo;

public interface RelInfo extends FieldInfo{

	public String getMappedBy();
	
	public Class<?> getRelative();

	public default ClassInfo getReferInfo() {
		return null;
	}
	
}
