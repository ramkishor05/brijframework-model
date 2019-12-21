package org.brijframework.model.diffination.impl;

import org.brijframework.model.diffination.ModelPropertyDiffination;
import org.brijframework.model.diffination.ModelTypeDeffination;
import org.brijframework.model.diffination.asm.AbstractModelPropertyDiffinationGroup;

public class ModelPropertyDiffinationGroupImpl extends AbstractModelPropertyDiffinationGroup{

	public ModelPropertyDiffinationGroupImpl(ModelTypeDeffination owner) {
		super(owner);
	}

	@Override
	public ModelPropertyDiffination getType() {
		return null;
	}

}
