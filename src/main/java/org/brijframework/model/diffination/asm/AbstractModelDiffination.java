package org.brijframework.model.diffination.asm;

import org.brijframework.Access;
import org.brijframework.model.ModelDiffination;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class AbstractModelDiffination<E> implements ModelDiffination<E>{
	
	private String id;
	private String name;
	private Access access;
	
	@JsonProperty()
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
}
