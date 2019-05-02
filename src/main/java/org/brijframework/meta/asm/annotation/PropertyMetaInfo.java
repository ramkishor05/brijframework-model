package org.brijframework.meta.asm.annotation;

import java.lang.reflect.AccessibleObject;
import java.util.Map;

import org.brijframework.meta.asm.AbstractFieldMetaInfo;
import org.brijframework.meta.reflect.ClassMetaInfo;

public class PropertyMetaInfo extends AbstractFieldMetaInfo {
	
	public PropertyMetaInfo(AccessibleObject target) {
		this.setTarget(target);
	}

	public PropertyMetaInfo(ClassMetaInfo owner, AccessibleObject target) {
		this(target);
		this.setOwner(owner);
	}

	@Override
	public void papulate(Map<String, Object> properties) {

	}
}
