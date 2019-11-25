package org.brijframework.model.info.asm;

import org.brijframework.model.info.ClassModelMetaData;
import org.brijframework.model.info.RelationPropertyModelMetaData;

public abstract class AbstractRelationPropertyModelMetaData extends AbstractPropertyModelMetaData implements RelationPropertyModelMetaData{

	private String mappedBy;
	
	private ClassModelMetaData targetOwner;
	
	public void setMappedBy(String mappedBy) {
		this.mappedBy = mappedBy;
	}
	
	@Override
	public String getMappedBy() {
		return mappedBy;
	}

	public ClassModelMetaData getTargetOwner() {
		return targetOwner;
	}

	public void setTargetOwner(ClassModelMetaData targetOwner) {
		this.targetOwner = targetOwner;
	}
}
