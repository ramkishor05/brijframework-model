package org.brijframework.model.resource.asm;

import org.brijframework.model.resource.RelationPropertyModelResource;

public class AbstractRelationPropertyModelResource extends AbstractPropertyModelResource<String> implements RelationPropertyModelResource<String> {

	private String mappedBy;

	@Override
	public String getMappedBy() {
		return mappedBy;
	}

	public void setMappedBy(String mappedBy) {
		this.mappedBy = mappedBy;
	}

}
