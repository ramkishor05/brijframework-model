package org.brijframework.meta.impl;

import org.brijframework.meta.asm.AbstractClassMetaInfo;
import org.brijframework.meta.info.ConstMetaInfo;
import org.brijframework.meta.info.FieldMetaInfo;

public class ClassMetaInfoImpl extends AbstractClassMetaInfo{
	
	private ConstMetaInfo constructor;
	
	public ClassMetaInfoImpl(Class<?> target) {
		this.setTarget(target);
	}
	
	public ClassMetaInfoImpl(Class<?> target,String id, String name) {
		this(target);
		this.setId(id);
		this.setName(name);
	}

	@Override
	public FieldMetaInfo getPropertyInfo(String _key) {
		return null;
	}

	@Override
	public ConstMetaInfo getConstructor() {
		return constructor;
	}
	
	public void setConstructor(ConstMetaInfo constructor) {
		this.constructor=constructor;
	}

	
}
