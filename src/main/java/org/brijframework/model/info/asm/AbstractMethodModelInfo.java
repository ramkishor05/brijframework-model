package org.brijframework.model.info.asm;

import java.lang.reflect.Method;

import org.brijframework.model.info.OwnerModelInfo;
import org.brijframework.model.info.MethodModelInfo;

public abstract class AbstractMethodModelInfo extends AbstractModelInfo<Method> implements MethodModelInfo {

	private OwnerModelInfo owner;
	private Method target;
	private Object value;
	
	public OwnerModelInfo getOwner() {
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
