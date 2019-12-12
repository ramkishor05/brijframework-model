package org.brijframework.model.diffination.impl;

import java.util.Map;

import org.brijframework.model.diffination.TypeModelDiffination;
import org.brijframework.model.diffination.asm.AbstractConstructorModelMetaData;

public class ConstructorModelMetaDataObject extends AbstractConstructorModelMetaData{
	
	public ConstructorModelMetaDataObject(TypeModelDiffination owner) {
		this.setOwner(owner);
	}

	@Override
	public void papulate(Map<String, Object> map) {
	}
}
