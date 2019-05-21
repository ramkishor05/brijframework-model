package org.brijframework.meta.impl;

import java.lang.reflect.AccessibleObject;

import org.brijframework.meta.reflect.ClassMeta;
import org.brijframework.support.enums.Wiring;

public class RelPtpMeta extends PropertyMeta implements org.brijframework.meta.reflect.ReferMeta{

	Class<?> mappedTo;
	
	String mappedBy;
	
	Wiring referred;
	
	public RelPtpMeta(AccessibleObject target) {
		super(target);
	}
	
	public RelPtpMeta(ClassMeta owner, AccessibleObject target) {
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
	public ClassMeta getRelationClassMeta() {
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
