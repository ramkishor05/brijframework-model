package org.brijframework.model.resource.asm;

import org.brijframework.model.ModelResource;

public abstract class AbstractModelResource<E> implements ModelResource<E> {
	
	private String id;
	private String name;
	private E target;
	private String access;

	public String getId() {
		return this.id;
	}

	public String getName() {
		return name;
	}

	public E getTarget() {
		return target;
	}

	public String getAccess() {
		return this.access;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTarget(E target) {
		this.target = target;
	}

	public void setAccess(String access) {
		this.access = access;
	}
	
}
