package org.brijframework.model;

import org.brijframework.GenericResource;
import org.brijframework.util.support.Access;

public interface ModelDiffination<E> extends GenericResource<E>{
	
	public Access getAccess();
	
}
