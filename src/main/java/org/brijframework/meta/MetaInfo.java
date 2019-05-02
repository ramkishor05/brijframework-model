package org.brijframework.meta;

import org.brijframework.lifecycle.Initializer;
import org.brijframework.support.enums.Scope;
import org.brijframework.util.support.Access;

public interface MetaInfo extends Initializer{
	
	public String getId();
	
	public String getName();

	public Access getAccess();
	
	public Scope getScope();
	
	public <T> T getTarget();
	
	public KeyInfo getKeyInfo();
	
	void setKeyInfo(KeyInfo keyInfo);
}
