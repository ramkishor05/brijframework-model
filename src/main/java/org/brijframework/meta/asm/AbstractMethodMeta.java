package org.brijframework.meta.asm;

import java.lang.reflect.Method;

import org.brijframework.meta.reflect.ClassMeta;
import org.brijframework.meta.reflect.MethodMeta;

public abstract class AbstractMethodMeta extends AbstractMetaInfo<Method> implements MethodMeta {

	private ClassMeta owner;
	private Method target;
	private Object value;
	
	public ClassMeta getOwner() {
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
