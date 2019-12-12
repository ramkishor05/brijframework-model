package org.brijframework.model.diffination.asm;

import java.lang.reflect.Method;

import org.brijframework.model.diffination.MethodModelMetaData;
import org.brijframework.model.diffination.TypeModelDiffination;

public abstract class AbstractMethodModelMetaData extends AbstractModelMetaData<Method> implements MethodModelMetaData {

	private TypeModelDiffination owner;
	private Method target;
	private Object value;
	
	public TypeModelDiffination getOwner() {
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
