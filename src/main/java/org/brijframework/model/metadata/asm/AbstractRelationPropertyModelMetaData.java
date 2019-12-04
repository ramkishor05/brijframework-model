package org.brijframework.model.metadata.asm;

import org.brijframework.model.metadata.RelationPropertyModelMetaData;
import org.brijframework.model.metadata.TypeModelMetaData;

public abstract class AbstractRelationPropertyModelMetaData extends AbstractPropertyModelMetaData implements RelationPropertyModelMetaData{

	private String mappedBy;
	
	private TypeModelMetaData targetOwner;
	
	public void setMappedBy(String mappedBy) {
		this.mappedBy = mappedBy;
	}
	
	@Override
	public String getMappedBy() {
		return mappedBy;
	}

	public TypeModelMetaData getTargetOwner() {
		return targetOwner;
	}

	public void setTargetOwner(TypeModelMetaData targetOwner) {
		this.targetOwner = targetOwner;
	}
}
