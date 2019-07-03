package org.brijframework.model.setup;

import org.brijframework.model.ModelSetup;

public interface FieldMetaSetup extends ModelSetup<String> {

	boolean isRequired();

	Object getValue();

	String getType();

}
