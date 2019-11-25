package org.brijframework.model.resource.asm;

import org.brijframework.model.resource.RelationPropertyModelResource;

public class AbstractRelationPropertyModelResource extends AbstractPropertyModelResource<String> implements RelationPropertyModelResource<String> {

	private String refer;

	@Override
	public String getRefer() {
		return this.refer;
	}

	public void setRefer(String refer) {
		this.refer = refer;
	}

	private String mappedTo;

	private String mappedBy;

	private String wired;

	@Override
	public String getMappedBy() {
		return mappedBy;
	}

	public void setMappedBy(String mappedBy) {
		this.mappedBy = mappedBy;
	}

	@Override
	public String getMappedTo() {
		return mappedTo;
	}

	public void setMappedTo(String mappedTo) {
		this.mappedTo = mappedTo;
	}

	@Override
	public String getWired() {
		return wired;
	}

	public void setWired(String wired) {
		this.wired = wired;
	}
}
