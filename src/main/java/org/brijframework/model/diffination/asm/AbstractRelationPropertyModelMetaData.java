package org.brijframework.model.diffination.asm;

import org.brijframework.model.diffination.RelationPropertyModelMetaData;
import org.brijframework.model.diffination.TypeModelDiffination;

public abstract class AbstractRelationPropertyModelMetaData extends AbstractPropertyModelMetaData implements RelationPropertyModelMetaData{

	private String mappedBy;
	
	private TypeModelDiffination targetOwner;
	
	public void setMappedBy(String mappedBy) {
		this.mappedBy = mappedBy;
	}
	
	@Override
	public String getMappedBy() {
		return mappedBy;
	}

	public TypeModelDiffination getTargetOwner() {
		return targetOwner;
	}

	public void setTargetOwner(TypeModelDiffination targetOwner) {
		this.targetOwner = targetOwner;
	}
}
