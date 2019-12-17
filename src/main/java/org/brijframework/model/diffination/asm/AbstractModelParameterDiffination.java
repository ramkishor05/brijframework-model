package org.brijframework.model.diffination.asm;

import java.lang.reflect.Parameter;

import org.brijframework.model.diffination.ModelParameterDiffination;

public abstract class AbstractModelParameterDiffination extends AbstractModelDiffination<Parameter> implements ModelParameterDiffination<Parameter>{

	private Parameter type;
	private Object value;
	
	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public Parameter getType() {
		return type;
	}

	public void setType(Parameter type) {
		this.type = type;
	}

	public void setValue(Object value) {
		this.value = value;
	}
	
}
