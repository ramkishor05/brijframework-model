package org.brijframework.model.resource;

import java.util.List;

import org.brijframework.model.ModelResource;

public interface ConstructorModelResource extends ModelResource<String>{

	List<ParameterModelResource<?>> getParameterList();

}
