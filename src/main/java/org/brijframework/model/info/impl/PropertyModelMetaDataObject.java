package org.brijframework.model.info.impl;

import java.lang.reflect.AccessibleObject;

import org.brijframework.model.info.ClassModelMetaData;
import org.brijframework.model.info.asm.AbstractPropertyModelMetaData;

public class PropertyModelMetaDataObject extends AbstractPropertyModelMetaData {
	
	public PropertyModelMetaDataObject(AccessibleObject target) {
		this.setTarget(target);
	}

	public PropertyModelMetaDataObject(ClassModelMetaData owner, AccessibleObject target) {
		this(target);
		this.setOwner(owner);
	}
}
