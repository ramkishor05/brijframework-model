package org.brijframework.model;

import org.brijframework.lifecycle.Initializer;

public interface ModelResource<E> extends Initializer{

	String getId();

	String getName();

	E getType();

	String getAccess();
}
