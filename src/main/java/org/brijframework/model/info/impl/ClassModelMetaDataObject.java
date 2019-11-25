package org.brijframework.model.info.impl;

import org.brijframework.model.info.asm.AbstractClassModelMetaData;

public class ClassModelMetaDataObject extends AbstractClassModelMetaData{

	public ClassModelMetaDataObject(Class<?> targetClass, String id, String name) {
		this.setTarget(targetClass);
		this.setId(id);
		this.setName(name);
	}

	

}
