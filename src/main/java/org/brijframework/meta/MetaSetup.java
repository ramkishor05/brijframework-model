package org.brijframework.meta;

public interface MetaSetup<T> {

	String getId();

	String getName();

	T getTarget();

	String getAccess();
}
