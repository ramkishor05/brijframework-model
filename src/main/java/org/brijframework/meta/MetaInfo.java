package org.brijframework.meta;

import org.brijframework.lifecycle.Initializer;
import org.brijframework.meta.info.ClassMetaInfo;
import org.brijframework.util.support.Access;

public interface MetaInfo<E> extends Initializer{
	
	public String getId();
	
	public String getName();

	public Access getAccess();
	
	public E getTarget();
	
	public ClassMetaInfo getOwner();
	
	public  void setOwner(ClassMetaInfo owner);
}
