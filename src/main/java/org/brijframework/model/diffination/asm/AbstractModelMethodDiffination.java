package org.brijframework.model.diffination.asm;

import java.lang.reflect.Method;

import org.brijframework.model.diffination.ModelMethodDiffination;
import org.brijframework.model.diffination.ModelTypeDeffination;

public abstract class AbstractModelMethodDiffination extends AbstractModelDiffination<Method> implements ModelMethodDiffination {

	private ModelTypeDeffination owner;
	private Method target;
	private Object value;
	
	public ModelTypeDeffination getOwner() {
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
