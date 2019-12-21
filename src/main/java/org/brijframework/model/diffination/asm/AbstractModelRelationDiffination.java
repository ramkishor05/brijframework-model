package org.brijframework.model.diffination.asm;

import org.brijframework.model.diffination.ModelRelationDiffination;
import org.brijframework.model.diffination.ModelTypeDeffination;

public abstract class AbstractModelRelationDiffination extends AbstractModelPropertyDiffination implements ModelRelationDiffination{

	private String mappedBy;
	
	private ModelTypeDeffination targetOwner;
	
	public void setMappedBy(String mappedBy) {
		this.mappedBy = mappedBy;
	}
	
	@Override
	public String getMappedBy() {
		return mappedBy;
	}

	public ModelTypeDeffination getTargetOwner() {
		return targetOwner;
	}

	public void setTargetOwner(ModelTypeDeffination targetOwner) {
		this.targetOwner = targetOwner;
	}
}
