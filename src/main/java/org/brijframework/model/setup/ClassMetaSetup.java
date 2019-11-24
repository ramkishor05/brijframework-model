package org.brijframework.model.setup;

import java.util.Map;

import org.brijframework.model.ModelResource;

public interface ClassMetaSetup extends ModelResource<String>{

	String getScope();
	
	String getType();
	
	Map<String, FieldMetaSetup> getProperties();

	ConstMetaSetup getConstructor();

	String getExtend();

}
