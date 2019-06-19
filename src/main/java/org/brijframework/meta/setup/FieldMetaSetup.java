package org.brijframework.meta.setup;

import org.brijframework.meta.MetaSetup;

public interface FieldMetaSetup extends MetaSetup<String> {

	boolean isRequired();

	Object getValue();

	String getType();

}
