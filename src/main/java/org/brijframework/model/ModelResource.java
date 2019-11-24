package org.brijframework.model;

public interface ModelResource<E> {

	String getId();

	String getName();

	E getTarget();

	String getAccess();
}
