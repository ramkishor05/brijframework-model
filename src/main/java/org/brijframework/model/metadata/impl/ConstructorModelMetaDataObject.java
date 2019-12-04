package org.brijframework.model.metadata.impl;

import java.util.Map;

import org.brijframework.model.metadata.TypeModelMetaData;
import org.brijframework.model.metadata.asm.AbstractConstructorModelMetaData;

public class ConstructorModelMetaDataObject extends AbstractConstructorModelMetaData{
	
	public ConstructorModelMetaDataObject(TypeModelMetaData owner) {
		this.setOwner(owner);
	}

	@Override
	public void papulate(Map<String, Object> map) {
	}
}
