package org.brijframework.meta.impl;

import org.brijframework.meta.asm.AbstractKeyInfo;
import org.brijframework.meta.reflect.ClassMeta;

public class PropertyKey extends AbstractKeyInfo<Object>{
	
	private PropertyMeta inner;
	public PropertyKey(PropertyMeta inner) {
		this.inner=inner;
	}

	@Override
	public ClassMeta getOwner() {
		return inner.getOwner();
	}

	@Override
	public void init() {
		this.setId(inner.getOwner().getId()+"_"+inner.getId());
		this.setName(inner.getName());
		this.setTarget(inner.getTarget());
		inner.setKeyInfo(this);
	}
}
