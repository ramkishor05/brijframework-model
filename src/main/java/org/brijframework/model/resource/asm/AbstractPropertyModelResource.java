package org.brijframework.model.resource.asm;

import org.brijframework.model.resource.PropertyModelResource;

public class AbstractPropertyModelResource<E> extends AbstractModelResource<E> implements PropertyModelResource<E> {

	private boolean required;
	private String type;
	private Object value;

	@Override
	public boolean isRequired() {
		return required;
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public String getType() {
		return type;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}
