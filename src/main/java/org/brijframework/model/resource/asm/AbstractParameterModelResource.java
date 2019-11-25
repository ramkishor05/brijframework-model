package org.brijframework.model.resource.asm;

import java.lang.reflect.Parameter;
import java.lang.reflect.Type;

import org.brijframework.model.resource.ParameterModelResource;

public abstract class AbstractParameterModelResource extends AbstractModelResource<Parameter> implements ParameterModelResource<Parameter> {

	private Class<?> parent;
	private Type type;
	private int index;
	private Object value;
	
	public Class<?> getParent() {
		return parent;
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
