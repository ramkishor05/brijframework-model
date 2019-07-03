package org.brijframework.model.info.asm;

import org.brijframework.model.info.OwnerModelInfo;
import org.brijframework.model.info.RelMetaInfo;

public abstract class AbstractRelModelInfo extends AbstractPptModelInfo implements RelMetaInfo{

	private String mappedBy;
	
	private OwnerModelInfo targetOwner;
	
	public void setMappedBy(String mappedBy) {
		this.mappedBy = mappedBy;
	}
	
	@Override
	public String getMappedBy() {
		return mappedBy;
	}

	public OwnerModelInfo getTargetOwner() {
		return targetOwner;
	}

	public void setTargetOwner(OwnerModelInfo targetOwner) {
		this.targetOwner = targetOwner;
	}
}
