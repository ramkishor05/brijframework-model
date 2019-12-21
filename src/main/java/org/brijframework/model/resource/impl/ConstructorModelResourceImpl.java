package org.brijframework.model.resource.impl;

import org.brijframework.model.resource.asm.AbstractConstructorModelResource;

public class ConstructorModelResourceImpl extends AbstractConstructorModelResource<String> {

	@Override
	public String toString() {
		return "ConstructorModelResourceImpl ["
				+ "id=" + getId() + ", "
				+ "name=" + getName() + ", "
				+ "type=" + getType() + ", "
				+ "access=" + getAccess() +", " 
				+ "parameterList=" + getParameterList()+"]";
	}

	
}
