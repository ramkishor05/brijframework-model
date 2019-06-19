package org.brijframework.meta.setup;

import java.util.Map;

import org.brijframework.meta.MetaSetup;

public interface ClassMetaSetup extends MetaSetup<String>{

	String getScope();
	
	String getType();
	
	Map<String, FieldMetaSetup> getProperties();

	ConstMetaSetup getConstructor();

}
