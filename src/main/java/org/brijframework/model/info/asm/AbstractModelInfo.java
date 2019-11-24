package org.brijframework.model.info.asm;

import org.brijframework.model.MetaData;
import org.brijframework.model.info.OwnerModelInfo;
import org.brijframework.util.support.Access;

public abstract class AbstractModelInfo<E> implements MetaData<E>{
	private String id;
	private String name;
	private Access access;
	private OwnerModelInfo owner;
	
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String getId() {
		return id;
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

	@Override
	public OwnerModelInfo getOwner() {
		return owner;
	}
	
	@Override
	public void setOwner(OwnerModelInfo owner) {
		this.owner = owner;
	}

}
