package org.brijframework.meta.asm;

import org.brijframework.meta.reflect.ClassMeta;
import org.brijframework.meta.reflect.ReferMeta;

public abstract class AbstractRelationMeta extends AbstractFieldMeta implements ReferMeta{

	private String mappedBy;
	
	private ClassMeta targetOwner;
	
	public void setMappedBy(String mappedBy) {
		this.mappedBy = mappedBy;
	}
	
	@Override
	public String getMappedBy() {
		return mappedBy;
	}

	public ClassMeta getTargetOwner() {
		return targetOwner;
	}

	public void setTargetOwner(ClassMeta targetOwner) {
		this.targetOwner = targetOwner;
	}
}
