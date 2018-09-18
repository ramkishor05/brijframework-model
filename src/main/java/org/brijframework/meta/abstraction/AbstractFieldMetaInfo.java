package org.brijframework.meta.abstraction;

import java.lang.reflect.AccessibleObject;

import org.brijframework.meta.KeyInfo;
import org.brijframework.meta.reflect.ClassMetaInfo;
import org.brijframework.meta.reflect.FieldMetaInfo;

public abstract class AbstractFieldMetaInfo extends AbstractMetaInfo implements FieldMetaInfo {

	private Class<?> parent;
	private AccessibleObject target;
	private KeyInfo keyInfo ;
	private ClassMetaInfo classMetaInfo;
	private Object value;
	
	public void setParent(Class<?> parent) {
		this.parent = parent;
	}
	
	@Override
	public Class<?> getParent() {
		return parent;
	}
	
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
	public ClassMetaInfo getClassMetaInfo() {
		return classMetaInfo;
	}
	
	public void setClassMetaInfo(ClassMetaInfo classMetaInfo) {
		this.classMetaInfo = classMetaInfo;
	}

}
