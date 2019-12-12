package org.brijframework.model.diffination.impl;

import java.lang.reflect.AccessibleObject;

import org.brijframework.model.diffination.TypeModelDiffination;
import org.brijframework.model.diffination.asm.AbstractPropertyModelMetaData;

public class PropertyModelMetaDataObject extends AbstractPropertyModelMetaData {
	
	public PropertyModelMetaDataObject(AccessibleObject target) {
		this.setType(target);
	}

	public PropertyModelMetaDataObject(TypeModelDiffination owner, AccessibleObject target) {
		this(target);
		this.setOwner(owner);
	}
}
