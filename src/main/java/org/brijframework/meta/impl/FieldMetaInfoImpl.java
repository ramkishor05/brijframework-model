package org.brijframework.meta.impl;

import java.lang.reflect.AccessibleObject;

import org.brijframework.meta.asm.AbstractFieldMetaInfo;
import org.brijframework.meta.info.ClassMetaInfo;

public class FieldMetaInfoImpl extends AbstractFieldMetaInfo {
	
	public FieldMetaInfoImpl(AccessibleObject target) {
		this.setTarget(target);
	}

	public FieldMetaInfoImpl(ClassMetaInfo owner, AccessibleObject target) {
		this(target);
		this.setOwner(owner);
	}
}
