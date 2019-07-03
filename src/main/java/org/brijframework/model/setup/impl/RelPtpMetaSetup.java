package org.brijframework.model.setup.impl;

public class RelPtpMetaSetup extends PropertyMetaSetup {

	private String mappedTo;

	private String mappedBy;

	private String wired;

	public String getMappedBy() {
		return mappedBy;
	}

	public void setMappedBy(String mappedBy) {
		this.mappedBy = mappedBy;
	}

	public String getMappedTo() {
		return mappedTo;
	}

	public void setMappedTo(String mappedTo) {
		this.mappedTo = mappedTo;
	}

	public String getWired() {
		return wired;
	}

	public void setWired(String wired) {
		this.wired = wired;
	}

	
}
