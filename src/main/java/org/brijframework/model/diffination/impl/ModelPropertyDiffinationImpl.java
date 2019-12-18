package org.brijframework.model.diffination.impl;

import java.lang.reflect.AccessibleObject;

import org.brijframework.model.diffination.ModelTypeDiffination;
import org.brijframework.model.diffination.asm.AbstractModelPropertyDiffination;

public class ModelPropertyDiffinationImpl extends AbstractModelPropertyDiffination {
	
	public ModelPropertyDiffinationImpl(AccessibleObject target) {
		this.setType(target);
	}

	public ModelPropertyDiffinationImpl(ModelTypeDiffination owner, AccessibleObject target) {
		this(target);
		this.setOwner(owner);
	}
	
}
