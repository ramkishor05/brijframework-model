package org.brijframework.model.resource.asm;

import org.brijframework.model.resource.PropertyModelResource;

public class AbstractPropertyModelResource<E> extends AbstractModelResource<E> implements PropertyModelResource<E> {

	private boolean required;
	private E type;
	private Object value;
	private String model;
	
	public void setModel(String model) {
		this.model = model;
	}
	
	@Override
	public String getModel() {
		return this.model;
	}

	@Override
	public boolean isRequired() {
		return required;
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public E getType() {
		return type;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public void setType(E type) {
		this.type = type;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}
