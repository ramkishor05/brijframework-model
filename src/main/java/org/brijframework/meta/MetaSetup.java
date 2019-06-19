package org.brijframework.meta;

public interface MetaSetup<E> {

	String getId();

	String getName();

	E getTarget();

	String getAccess();
}
