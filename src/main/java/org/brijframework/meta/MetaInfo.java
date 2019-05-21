package org.brijframework.meta;

import org.brijframework.lifecycle.Initializer;
import org.brijframework.meta.reflect.ClassMeta;
import org.brijframework.support.enums.Scope;
import org.brijframework.util.support.Access;

public interface MetaInfo<E> extends Initializer{
	
	public String getId();
	
	public String getName();

	public Access getAccess();
	
	public Scope getScope();
	
	public E getTarget();
	
	public KeyInfo getKeyInfo();
	
	void setKeyInfo(KeyInfo keyInfo);

	public ClassMeta getOwner();
	
	public  void setOwner(ClassMeta owner);
}
