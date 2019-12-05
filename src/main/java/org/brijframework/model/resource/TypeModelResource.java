package org.brijframework.model.resource;

import java.util.Map;

import org.brijframework.model.ModelResource;

public interface TypeModelResource extends ModelResource<String>{

	String getType();
	
	String getExtend();
	
	ConstructorModelResource getConstructor();
		
	Map<String, PropertyModelResource<?>> getProperties();

}
