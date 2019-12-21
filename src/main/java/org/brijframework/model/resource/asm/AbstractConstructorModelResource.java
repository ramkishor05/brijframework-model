package org.brijframework.model.resource.asm;

import java.util.ArrayList;
import java.util.List;

import org.brijframework.model.resource.ConstructorModelResource;
import org.brijframework.model.resource.ParameterModelResource;

public class AbstractConstructorModelResource<E> extends AbstractModelResource<E>
		implements ConstructorModelResource<E> {

	private E type;
	private List<ParameterModelResource> parameterList;

	@Override
	public E getType() {
		return type;
	}

	@Override
	public List<ParameterModelResource> getParameterList() {
		if(parameterList==null) {
			parameterList=new ArrayList<ParameterModelResource>();
		}
		return parameterList;
	}

	public void setType(E type) {
		this.type = type;
	}

	public void setParameterList(List<ParameterModelResource> parameterList) {
		this.parameterList = parameterList;
	}

}
