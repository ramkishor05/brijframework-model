package org.brijframework.model.diffination.impl;

import org.brijframework.model.diffination.ModelTypeDeffination;
import org.brijframework.model.diffination.asm.AbstractModelMethodDiffination;

public class ModelMethodDiffinationImpl extends AbstractModelMethodDiffination{
	
	public ModelMethodDiffinationImpl(ModelTypeDeffination owner) {
		this.setOwner(owner);
	}
	
}
