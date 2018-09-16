package org.brijframework.meta;

import org.brijframework.lifecycle.Initializer;
import org.brijframework.support.enums.Access;
import org.brijframework.support.enums.Scope;

public interface MetaInfo extends Initializer{
	
	public String getId();
	
	public String getParentID();
	
	public String getName();

	public Access getAccess();
	
	public Scope getScope();
	
	public <T> T getTarget();
	
	public Class<?> getParent();

	public KeyInfo getKeyInfo();
}
