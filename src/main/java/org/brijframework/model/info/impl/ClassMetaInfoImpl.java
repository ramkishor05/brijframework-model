package org.brijframework.model.info.impl;

import org.brijframework.model.info.ConstModelInfo;
import org.brijframework.model.info.PptModelInfo;
import org.brijframework.model.info.asm.AbstractClassModelInfo;

public class ClassMetaInfoImpl extends AbstractClassModelInfo{
	
	private ConstModelInfo constructor;
	
	public ClassMetaInfoImpl(Class<?> target) {
		this.setTarget(target);
	}
	
	public ClassMetaInfoImpl(Class<?> target,String id, String name) {
		this(target);
		this.setId(id);
		this.setName(name);
	}

	@Override
	public PptModelInfo getPropertyInfo(String _key) {
		return null;
	}

	@Override
	public ConstModelInfo getConstructor() {
		return constructor;
	}
	
	public void setConstructor(ConstModelInfo constructor) {
		this.constructor=constructor;
	}

	
}
