package org.brijframework.model.metadata.impl;

import org.brijframework.model.metadata.PropertyModelMetaData;
import org.brijframework.model.metadata.TypeModelMetaData;
import org.brijframework.model.metadata.asm.AbstractPropertyModelMetaDataGroup;

public class PropertyModelMetaDataGroupObject extends AbstractPropertyModelMetaDataGroup{

	public PropertyModelMetaDataGroupObject(TypeModelMetaData owner) {
		super(owner);
	}

	@Override
	public PropertyModelMetaData getType() {
		return null;
	}

}
