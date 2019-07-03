package org.brijframework.model.info.asm;

import java.util.HashMap;
import java.util.Map;

import org.brijframework.model.info.OwnerModelInfo;
import org.brijframework.model.info.PptModelInfoGroup;

public abstract class AbstractClassModelInfo extends AbstractModelInfo<Class<?>> implements OwnerModelInfo{
	
	private Map<String,PptModelInfoGroup>  properties;
	
	private Class<?> target;
	private String type;
	
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String getType() {
		return type;
	}
	
	public void setTarget(Class<?> target) {
		this.target = target;
	}

	@Override
	public Class<?> getTarget() {
		return target;
	}

	@Override
	public Map<String, PptModelInfoGroup> getProperties() {
		if(properties==null) {
			properties=new HashMap<String, PptModelInfoGroup>();
		}
		return properties;
	}
}
