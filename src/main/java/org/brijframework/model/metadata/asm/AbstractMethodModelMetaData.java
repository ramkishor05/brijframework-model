package org.brijframework.model.metadata.asm;

import java.lang.reflect.Method;

import org.brijframework.model.metadata.MethodModelMetaData;
import org.brijframework.model.metadata.TypeModelMetaData;

public abstract class AbstractMethodModelMetaData extends AbstractModelMetaData<Method> implements MethodModelMetaData {

	private TypeModelMetaData owner;
	private Method target;
	private Object value;
	
	public TypeModelMetaData getOwner() {
		return owner;
	}
	
	public void setTarget(Method target) {
		this.target = target;
	}
	
	@Override
	public Method getType() {
		return target;
	}
	
	public Object getValue() {
		return value;
	}
	
	public void setValue(Object value) {
		this.value = value;
	}

}
