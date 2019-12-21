package org.brijframework.model.resource.impl;

import org.brijframework.model.resource.asm.AbstractParameterModelResource;

public class ParameterModelResourceImpl extends AbstractParameterModelResource {

	@Override
	public String toString() {
		return "ParameterModelResourceImpl ["
				+ "id=" + getId() + ", "
				+ "index=" + getIndex() + ", "
				+ "type=" + getType() + ", "
				+ "name=" + getName() + ", "
				+ "access=" + getAccess() + "]";
	}

	
}
