package org.brijframework.meta.asm.annotation;

import java.util.Objects;

import org.brijframework.meta.asm.AbstractKeyInfo;
import org.brijframework.meta.reflect.ClassMetaInfo;

public class ClassKeyInfo extends AbstractKeyInfo<Class<?>>{

	private ClassMetaInfo owner;
	
	public ClassKeyInfo(ModelMetaInfo owner) {
		this.owner=owner;
		this.init();
	}
	
	@Override
	public ClassMetaInfo getOwner() {
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

