package org.brijframework.meta.impl;

import java.util.Map;

import org.brijframework.meta.asm.AbstractClassMeta;
import org.brijframework.meta.reflect.ConstMeta;
import org.brijframework.meta.reflect.FieldMeta;

public class ModelMeta extends AbstractClassMeta{
	
	private ConstMeta constructor;
	
	public ModelMeta(Class<?> target) {
		this.setTarget(target);
	}
	
	public ModelMeta(Class<?> target,String id, String name) {
		this(target);
		this.setId(id);
		this.setName(name);
	}

	@Override
	public void papulate(Map<String, Object> map) {
	}

	@Override
	public FieldMeta getPropertyInfo(String _key) {
		return null;
	}

	@Override
	public ConstMeta getConstructor() {
		return constructor;
	}
	
	public void setConstructor(ConstMeta constructor) {
		this.constructor=constructor;
	}

	
}
