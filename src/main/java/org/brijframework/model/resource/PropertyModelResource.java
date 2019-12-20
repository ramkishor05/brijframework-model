package org.brijframework.model.resource;

import org.brijframework.model.ModelResource;

public interface PropertyModelResource<E> extends ModelResource<E> {

	boolean isRequired();

	E getType();

	String getModel();

}
