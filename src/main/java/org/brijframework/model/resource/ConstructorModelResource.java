package org.brijframework.model.resource;

import java.util.List;

import org.brijframework.model.ModelResource;

public interface ConstructorModelResource<E> extends ModelResource<E>{

	List<ParameterModelResource> getParameterList();

}
