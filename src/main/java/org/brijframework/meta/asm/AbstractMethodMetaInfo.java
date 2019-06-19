package org.brijframework.meta.asm;

import java.lang.reflect.Method;

import org.brijframework.meta.info.ClassMetaInfo;
import org.brijframework.meta.info.MethodMetaInfo;

public abstract class AbstractMethodMetaInfo extends AbstractMetaInfo<Method> implements MethodMetaInfo {

	private ClassMetaInfo owner;
	private Method target;
	private Object value;
	
	public ClassMetaInfo getOwner() {
		return owner;
	}
	
	public void setTarget(Method target) {
		this.target = target;
	}
	
	@Override
	public Method getTarget() {
		return target;
	}
	
	public Object getValue() {
		return value;
	}
	
	public void setValue(Object value) {
		this.value = value;
	}

}
