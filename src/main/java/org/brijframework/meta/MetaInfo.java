package org.brijframework.meta;

import org.brijframework.lifecycle.Initializer;
import org.brijframework.meta.reflect.ClassMeta;
import org.brijframework.util.support.Access;

public interface MetaInfo<E> extends Initializer{
	
	public String getId();
	
	public String getName();

	public Access getAccess();
	
	public E getTarget();
	
	public ClassMeta getOwner();
	
	public  void setOwner(ClassMeta owner);
}
