package org.brijframework.meta.setup;

import java.util.Map;

import org.brijframework.meta.reflect.ConstMeta;

public interface ClassMetaSetup extends MetaSetup{

	String getScope();
	
	String getType();
	
	Map<String, FieldMetaSetup> getProperties();

	ConstMeta getConstructor();

}
