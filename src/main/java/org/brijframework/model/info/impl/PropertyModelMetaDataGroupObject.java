package org.brijframework.model.info.impl;

import org.brijframework.model.info.ClassModelMetaData;
import org.brijframework.model.info.PropertyModelMetaData;
import org.brijframework.model.info.asm.AbstractPropertyModelMetaDataGroup;

public class PropertyModelMetaDataGroupObject extends AbstractPropertyModelMetaDataGroup{

	public PropertyModelMetaDataGroupObject(ClassModelMetaData owner) {
		super(owner);
	}

	@Override
	public PropertyModelMetaData getTarget() {
		return null;
	}

}
