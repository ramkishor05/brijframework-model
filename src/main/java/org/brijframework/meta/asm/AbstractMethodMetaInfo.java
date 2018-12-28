package org.brijframework.meta.asm;

import java.lang.reflect.Method;

import org.brijframework.meta.KeyInfo;
import org.brijframework.meta.reflect.ClassMetaInfo;
import org.brijframework.meta.reflect.MethodMetaInfo;

public abstract class AbstractMethodMetaInfo extends AbstractMetaInfo implements MethodMetaInfo {

	private ClassMetaInfo owner;
	private Method target;
	private KeyInfo keyInfo ;
	private ClassMetaInfo classMetaInfo;
	private Object value;
	
	public ClassMetaInfo getOwner() {
		return owner;
	}
	
	public void setKeyInfo(KeyInfo keyInfo) {
		this.keyInfo = keyInfo;
	}

	@Override
	public KeyInfo getKeyInfo() {
		return keyInfo;
	}
	
	public void setTarget(Method target) {
		this.target = target;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Method getTarget() {
		return target;
	}
	
	@Override
	public ClassMetaInfo getClassMetaInfo() {
		return classMetaInfo;
	}
	
	public void setClassMetaInfo(ClassMetaInfo classMetaInfo) {
		this.classMetaInfo = classMetaInfo;
	}
	
	public Object getValue() {
		return value;
	}
	
	public void setValue(Object value) {
		this.value = value;
	}

}
