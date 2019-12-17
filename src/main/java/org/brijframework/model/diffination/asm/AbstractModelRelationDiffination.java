package org.brijframework.model.diffination.asm;

import org.brijframework.model.diffination.ModelRelationDiffination;
import org.brijframework.model.diffination.ModelTypeDiffination;

public abstract class AbstractModelRelationDiffination extends AbstractModelPropertyDiffination implements ModelRelationDiffination{

	private String mappedBy;
	
	private ModelTypeDiffination targetOwner;
	
	public void setMappedBy(String mappedBy) {
		this.mappedBy = mappedBy;
	}
	
	@Override
	public String getMappedBy() {
		return mappedBy;
	}

	public ModelTypeDiffination getTargetOwner() {
		return targetOwner;
	}

	public void setTargetOwner(ModelTypeDiffination targetOwner) {
		this.targetOwner = targetOwner;
	}
}
