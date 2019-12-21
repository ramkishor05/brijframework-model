package org.brijframework.model.diffination.impl;

import java.lang.reflect.AccessibleObject;

import org.brijframework.model.diffination.ModelTypeDeffination;
import org.brijframework.model.diffination.asm.AbstractModelPropertyDiffination;

public class ModelPropertyDiffinationImpl extends AbstractModelPropertyDiffination {
	
	public ModelPropertyDiffinationImpl(AccessibleObject target) {
		this.setType(target);
	}

	public ModelPropertyDiffinationImpl(ModelTypeDeffination owner, AccessibleObject target) {
		this(target);
		this.setOwner(owner);
	}
	
}
