package org.brijframework.meta.asm;

import java.lang.reflect.AccessibleObject;

import org.brijframework.meta.KeyInfo;
import org.brijframework.meta.reflect.ClassMetaInfo;
import org.brijframework.meta.reflect.FieldMetaInfo;

public abstract class AbstractFieldMetaInfo extends AbstractMetaInfo implements FieldMetaInfo {

	private AccessibleObject target;
	private KeyInfo keyInfo ;
	private ClassMetaInfo owner;
	private Object value;
	public boolean required;
	
	public void setKeyInfo(KeyInfo keyInfo) {
		this.keyInfo = keyInfo;
	}

	@Override
	public KeyInfo getKeyInfo() {
		return keyInfo;
	}
	
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
	
	@SuppressWarnings("unchecked")
	@Override
	public AccessibleObject getTarget() {
		return target;
	}
	
	@Override
	public ClassMetaInfo getOwner() {
		return owner;
	}
	
	public void setOwner(ClassMetaInfo owner) {
		this.owner = owner;
	}
	
	public boolean isRequired() {
		return required;
	}
	
	public void setRequired(boolean required) {
		this.required = required;
	}

}
