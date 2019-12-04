package org.brijframework.model.metadata.impl;

import java.lang.reflect.AccessibleObject;

import org.brijframework.model.metadata.TypeModelMetaData;
import org.brijframework.model.metadata.asm.AbstractPropertyModelMetaData;

public class PropertyModelMetaDataObject extends AbstractPropertyModelMetaData {
	
	public PropertyModelMetaDataObject(AccessibleObject target) {
		this.setType(target);
	}

	public PropertyModelMetaDataObject(TypeModelMetaData owner, AccessibleObject target) {
		this(target);
		this.setOwner(owner);
	}
}
