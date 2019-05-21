package org.brijframework.meta.impl;

import java.util.Objects;

import org.brijframework.meta.asm.AbstractKeyInfo;
import org.brijframework.meta.reflect.ClassMeta;

public class ClassKey extends AbstractKeyInfo<Class<?>>{

	private ClassMeta owner;
	
	public ClassKey(ClassMeta owner) {
		this.owner=owner;
		this.init();
	}
	
	@Override
	public ClassMeta getOwner() {
		return owner;
	}

	@Override
	public void init() {
		Objects.requireNonNull(this.getOwner(), "owner is required.");
		this.setId(this.getOwner().getId());
		this.setTarget(this.getOwner().getTarget());
		this.setName(this.getOwner().getName());
		this.getOwner().setKeyInfo(this);
	}
}

