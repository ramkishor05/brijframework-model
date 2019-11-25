package org.brijframework.model.info.asm;

import java.lang.reflect.Method;

import org.brijframework.model.info.ClassModelMetaData;
import org.brijframework.model.info.MethodModelMetaData;

public abstract class AbstractMethodModelMetaData extends AbstractModelMetaData<Method> implements MethodModelMetaData {

	private ClassModelMetaData owner;
	private Method target;
	private Object value;
	
	public ClassModelMetaData getOwner() {
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
