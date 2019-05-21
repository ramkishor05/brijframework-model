package org.brijframework.meta.impl;

import java.lang.reflect.AccessibleObject;
import java.util.Map;

import org.brijframework.meta.asm.AbstractFieldMeta;
import org.brijframework.meta.reflect.ClassMeta;

public class PropertyMeta extends AbstractFieldMeta {
	
	public PropertyMeta(AccessibleObject target) {
		this.setTarget(target);
	}

	public PropertyMeta(ClassMeta owner, AccessibleObject target) {
		this(target);
		this.setOwner(owner);
	}

	@Override
	public void papulate(Map<String, Object> properties) {

	}
}
