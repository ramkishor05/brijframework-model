package org.brijframework.model.config.asm;

import org.brijframework.model.config.ModelConfig;

public class ModelConfigration implements ModelConfig{
	
	private boolean enable;
	private String location;
	private String packages;

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
	
	public String getPackages() {
		return packages;
	}
	
	public void setPackages(String packages) {
		this.packages = packages;
	}
	
}
