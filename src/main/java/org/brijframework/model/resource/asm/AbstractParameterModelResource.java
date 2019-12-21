package org.brijframework.model.resource.asm;

import org.brijframework.model.resource.ParameterModelResource;

public abstract class AbstractParameterModelResource extends AbstractModelResource<String> implements ParameterModelResource{

	private String type;
	private int index;
	
	public void setIndex(int index) {
		this.index = index;
	}

	@Override
	public int getIndex() {
		return index;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String getType() {
		return type;
	}
}
