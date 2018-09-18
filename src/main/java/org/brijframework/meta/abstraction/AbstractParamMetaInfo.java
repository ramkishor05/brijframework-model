package org.brijframework.meta.abstraction;

import java.lang.reflect.Parameter;
import java.lang.reflect.Type;

import org.brijframework.meta.KeyInfo;
import org.brijframework.meta.reflect.ParamMetaInfo;

public abstract class AbstractParamMetaInfo extends AbstractMetaInfo implements ParamMetaInfo {

	private Class<?> parent;
	private Parameter target;
	private Type type;
	private KeyInfo keyInfo ;
	private int index;
	private Object value;
	
	public void setTarget(Parameter target) {
		this.target = target;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Parameter getTarget() {
		return target;
	}

	public void setParent(Class<?> parent) {
		this.parent = parent;
	}
	
	@Override
	public Class<?> getParent() {
		return parent;
	}
	
	public void setKeyInfo(KeyInfo keyInfo) {
		this.keyInfo = keyInfo;
	}

	@Override
	public KeyInfo getKeyInfo() {
		return keyInfo;
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
