package org.brijframework.meta.asm;

import java.lang.reflect.Type;

import org.brijframework.meta.KeyInfo;

public abstract class AbstractKeyInfo implements KeyInfo{
	
	private String id;
	private String parentID;
	private String name;

	private Type[] params;
	@Override
	public String getID() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Type[] getParams() {
		return params;
	}

	@Override
	public String getParentID() {
		return parentID;
	}

}
