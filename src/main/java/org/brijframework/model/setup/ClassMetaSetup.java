package org.brijframework.model.setup;

import java.util.Map;

import org.brijframework.model.ModelSetup;

public interface ClassMetaSetup extends ModelSetup<String>{

	String getScope();
	
	String getType();
	
	Map<String, FieldMetaSetup> getProperties();

	ConstMetaSetup getConstructor();

	String getExtend();

}
