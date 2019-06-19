package org.brijframework.meta.asm;

import org.brijframework.meta.info.ClassMetaInfo;
import org.brijframework.meta.info.RelMetaInfo;

public abstract class AbstractRelMetaInfo extends AbstractFieldMetaInfo implements RelMetaInfo{

	private String mappedBy;
	
	private ClassMetaInfo targetOwner;
	
	public void setMappedBy(String mappedBy) {
		this.mappedBy = mappedBy;
	}
	
	@Override
	public String getMappedBy() {
		return mappedBy;
	}

	public ClassMetaInfo getTargetOwner() {
		return targetOwner;
	}

	public void setTargetOwner(ClassMetaInfo targetOwner) {
		this.targetOwner = targetOwner;
	}
}
