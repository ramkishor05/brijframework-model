package org.brijframework.model.diffination.impl;

import org.brijframework.model.diffination.PropertyModelMetaData;
import org.brijframework.model.diffination.TypeModelDiffination;
import org.brijframework.model.diffination.asm.AbstractPropertyModelMetaDataGroup;

public class PropertyModelMetaDataGroupObject extends AbstractPropertyModelMetaDataGroup{

	public PropertyModelMetaDataGroupObject(TypeModelDiffination owner) {
		super(owner);
	}

	@Override
	public PropertyModelMetaData getType() {
		return null;
	}

}
