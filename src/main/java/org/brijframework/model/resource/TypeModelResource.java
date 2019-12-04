package org.brijframework.model.resource;

import java.util.Map;

import org.brijframework.model.ModelResource;

public interface TypeModelResource extends ModelResource<String>{

	String getScope();
	
	String getType();
		
	Map<String, PropertyModelResource<?>> getProperties();

	ConstructorModelResource getConstructor();

	String getExtend();

}
