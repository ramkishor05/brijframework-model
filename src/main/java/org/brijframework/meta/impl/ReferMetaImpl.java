package org.brijframework.meta.impl;

import java.lang.reflect.AccessibleObject;

import org.brijframework.meta.info.ClassMetaInfo;
import org.brijframework.support.enums.Wiring;

public class ReferMetaImpl extends FieldMetaInfoImpl implements org.brijframework.meta.info.RelMetaInfo{

	Class<?> mappedTo;
	
	String mappedBy;
	
	Wiring referred;
	
	public ReferMetaImpl(AccessibleObject target) {
		super(target);
	}
	
	public ReferMetaImpl(ClassMetaInfo owner, AccessibleObject target) {
		super(owner,target);
	}

	@Override
	public String getMappedBy() {
		return mappedBy;
	}

	@Override
	public Class<?> getTargetClass() {
		return mappedTo;
	}

	@Override
	public ClassMetaInfo getTargetClassMetaInfo() {
		return this.getOwner();
	}

	public Class<?> getMappedTo() {
		return mappedTo;
	}

	public void setMappedTo(Class<?> mappedTo) {
		this.mappedTo = mappedTo;
	}

	public Wiring getReferred() {
		return referred;
	}

	public void setReferred(Wiring referred) {
		this.referred = referred;
	}

	public void setMappedBy(String mappedBy) {
		this.mappedBy = mappedBy;
	}

	
}
