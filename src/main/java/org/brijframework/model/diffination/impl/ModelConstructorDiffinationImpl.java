package org.brijframework.model.diffination.impl;

import java.util.Map;

import org.brijframework.model.diffination.ModelTypeDiffination;
import org.brijframework.model.diffination.asm.AbstractModelConstructorDiffination;

public class ModelConstructorDiffinationImpl extends AbstractModelConstructorDiffination{
	
	public ModelConstructorDiffinationImpl(ModelTypeDiffination owner) {
		this.setOwner(owner);
	}

	@Override
	public void papulate(Map<String, Object> map) {
	}
}
