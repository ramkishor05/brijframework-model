package org.brijframework.model.info;

import java.util.Map;

import org.brijframework.model.ModelInfo;

public interface OwnerModelInfo extends ModelInfo<Class<?>> {

	public String getType();

	public Class<?> getTarget();

	public OwnerModelInfo getOwner();
	
	public PptModelInfo getPropertyInfo(String _key);

	public ConstModelInfo getConstructor();

	public Map<String, PptModelInfoGroup> getProperties();

}
