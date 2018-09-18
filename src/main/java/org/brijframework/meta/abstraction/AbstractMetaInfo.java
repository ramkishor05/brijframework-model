package org.brijframework.meta.abstraction;

import org.brijframework.meta.MetaInfo;
import org.brijframework.support.enums.Access;
import org.brijframework.support.enums.Scope;

public abstract class AbstractMetaInfo implements MetaInfo{
	private String id;
	private String parentID;
	private String name;
	private Access access;
	private Scope scope;
	
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String getId() {
		return id;
	}
	
	public void setParentID(String parentID) {
		this.parentID = parentID;
	}

	@Override
	public String getParentID() {
		return parentID;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}
	
	public void setAccess(Access access) {
		this.access = access;
	}

	@Override
	public Access getAccess() {
		return access;
	}
	
	public void setScope(Scope scope) {
		this.scope = scope;
	}

	@Override
	public Scope getScope() {
		return scope;
	}

}
