package org.brijframework.meta.asm;

import java.lang.reflect.AccessibleObject;

import org.brijframework.meta.reflect.FieldMeta;

public abstract class AbstractFieldMeta extends AbstractMetaInfo<AccessibleObject> implements FieldMeta {

	private AccessibleObject target;
	private Object value;
	public boolean required;
	
	public void setValue(Object value) {
		this.value = value;
	}

	@Override
	public Object getValue() {
		return value;
	}
	
	public void setTarget(AccessibleObject target) {
		this.target = target;
	}
	
	@Override
	public AccessibleObject getTarget() {
		return target;
	}
	
	
	public boolean isRequired() {
		return required;
	}
	
	public void setRequired(boolean required) {
		this.required = required;
	}

}
