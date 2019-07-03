package org.brijframework.model.info.impl;

import java.lang.reflect.AccessibleObject;

import org.brijframework.model.info.OwnerModelInfo;
import org.brijframework.model.info.asm.AbstractPptModelInfo;

public class FieldMetaInfoImpl extends AbstractPptModelInfo {
	
	public FieldMetaInfoImpl(AccessibleObject target) {
		this.setTarget(target);
	}

	public FieldMetaInfoImpl(OwnerModelInfo owner, AccessibleObject target) {
		this(target);
		this.setOwner(owner);
	}
}
