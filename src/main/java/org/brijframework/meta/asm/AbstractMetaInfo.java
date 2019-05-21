package org.brijframework.meta.asm;

import org.brijframework.meta.KeyInfo;
import org.brijframework.meta.MetaInfo;
import org.brijframework.meta.reflect.ClassMeta;
import org.brijframework.support.enums.Scope;
import org.brijframework.util.support.Access;

public abstract class AbstractMetaInfo<E> implements MetaInfo<E>{
	private String id;
	private String name;
	private Access access;
	private Scope scope;
	private KeyInfo keyInfo ;
	private ClassMeta owner;
	
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
	
	public void setScope(Scope scope) {
		this.scope = scope;
	}

	@Override
	public Scope getScope() {
		return scope;
	}

	@Override
	public void setKeyInfo(KeyInfo keyInfo) {
		this.keyInfo = keyInfo;
	}

	@Override
	public KeyInfo getKeyInfo() {
		return keyInfo;
	}
	

	@Override
	public ClassMeta getOwner() {
		return owner;
	}
	
	@Override
	public void setOwner(ClassMeta owner) {
		this.owner = owner;
	}

}
