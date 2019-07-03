package org.brijframework.model.info.asm;

import java.lang.reflect.Parameter;
import java.lang.reflect.Type;

import org.brijframework.model.info.ParameterModelInfo;

public abstract class AbstractParamModelInfo extends AbstractModelInfo<Parameter> implements ParameterModelInfo {

	private Class<?> parent;
	private Parameter target;
	private Type type;
	private int index;
	private Object value;
	
	public Class<?> getParent() {
		return parent;
	}

	public void setTarget(Parameter target) {
		this.target = target;
	}
	
	@Override
	public Parameter getTarget() {
		return target;
	}

	public void setParent(Class<?> parent) {
		this.parent = parent;
	}
	
	public void setIndex(int index) {
		this.index = index;
	}

	@Override
	public int getIndex() {
		return index;
	}

	public void setType(Type type) {
		this.type = type;
	}
	
	@Override
	public Type getType() {
		return type;
	}
	
	public void setValue(Object value) {
		this.value = value;
	}
	
	@Override
	public Object getValue() {
		return value;
	}

}
