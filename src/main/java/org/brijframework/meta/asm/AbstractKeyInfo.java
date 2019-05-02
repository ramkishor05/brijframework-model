package org.brijframework.meta.asm;

import java.lang.reflect.Type;

import org.brijframework.meta.KeyInfo;

public abstract class AbstractKeyInfo<T> implements KeyInfo{
	
	private String id;
	private T target;
	private String name;
	private Type[] params;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Type[] getParams() {
		return params;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getTarget() {
		return target;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setTarget(T target) {
		this.target = target;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setParams(Type[] params) {
		this.params = params;
	}

	@Override
	public String toString() {
		StringBuilder builder=new StringBuilder();
		if(getId()!=null) {
			builder.append(getId());
		}else {
			builder.append(getName());
		}
		return builder.toString();
	}
	
}
