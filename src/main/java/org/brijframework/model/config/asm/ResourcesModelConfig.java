package org.brijframework.model.config.asm;

import org.brijframework.model.config.ModelConfig;

public class ResourcesModelConfig implements ModelConfig{
	
	private boolean enable;
	private String location;

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLocation() {
		return location;
	}
	
}
