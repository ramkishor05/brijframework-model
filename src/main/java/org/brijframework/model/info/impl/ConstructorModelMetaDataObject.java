package org.brijframework.model.info.impl;

import java.util.Map;

import org.brijframework.model.info.ClassModelMetaData;
import org.brijframework.model.info.asm.AbstractConstructorModelMetaData;

public class ConstructorModelMetaDataObject extends AbstractConstructorModelMetaData{
	
	public ConstructorModelMetaDataObject(ClassModelMetaData owner) {
		this.setOwner(owner);
	}

	@Override
	public void papulate(Map<String, Object> map) {
	}
}
