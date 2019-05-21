package org.brijframework.meta.impl;

import java.util.Map;

import org.brijframework.meta.asm.AbstractConsMeta;
import org.brijframework.meta.reflect.ClassMeta;

public class ModelConsMeta extends AbstractConsMeta{
	
	public ModelConsMeta(ClassMeta owner) {
		this.setOwner(owner);
	}

	@Override
	public void papulate(Map<String, Object> map) {
	}
}
