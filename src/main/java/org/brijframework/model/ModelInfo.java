package org.brijframework.model;

import org.brijframework.lifecycle.Initializer;
import org.brijframework.model.info.OwnerModelInfo;
import org.brijframework.util.support.Access;

public interface ModelInfo<E> extends Initializer{
	
	public String getId();
	
	public String getName();

	public Access getAccess();
	
	public E getTarget();
	
	public OwnerModelInfo getOwner();
	
	public  void setOwner(OwnerModelInfo owner);
}
