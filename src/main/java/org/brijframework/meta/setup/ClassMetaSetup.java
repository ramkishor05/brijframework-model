package org.brijframework.meta.setup;

import java.util.Map;

public interface ClassMetaSetup extends MetaSetup{

	String getScope();
	
	String getType();
	
	Map<String, FieldMetaSetup> getProperties();

}
