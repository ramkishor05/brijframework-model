package org.brijframework.model;

public interface ModelSetup<E> {

	String getId();

	String getName();

	E getTarget();

	String getAccess();
}
