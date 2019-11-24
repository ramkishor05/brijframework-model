package org.brijframework.model.setup;

import org.brijframework.model.ModelResource;

public interface FieldMetaSetup extends ModelResource<String> {

	boolean isRequired();

	Object getValue();

	String getType();

}
