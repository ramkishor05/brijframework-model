package org.brijframework.model;

import org.brijframework.lifecycle.Initializer;
import org.brijframework.util.support.Access;

public interface ModelDiffination<E> extends Initializer{
	
	public String getId();
	
	public String getName();

	public Access getAccess();
	
	public E getType();
}
