package org.brijframework.model.diffination.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.brijframework.model.diffination.ModelTypeDiffination;
import org.brijframework.model.diffination.asm.AbstractModelRelationDiffination;
import org.brijframework.support.enums.MappedBy;

public class ModelRelationDiffinationImpl extends AbstractModelRelationDiffination{

	Class<?> mappedTo;
	
	String mappedBy;
	
	MappedBy referred;
	
	public ModelRelationDiffinationImpl(ModelTypeDiffination owner, Field target) {
		this.setType(target);
		this.setOwner(owner);
	}

	public ModelRelationDiffinationImpl(ModelTypeDiffination owner, Method target) {
		this.setType(target);
		this.setOwner(owner);
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
	public ModelTypeDiffination getTargetClassMetaInfo() {
		return this.getOwner();
	}

	public Class<?> getMappedTo() {
		return mappedTo;
	}

	public void setMappedTo(Class<?> mappedTo) {
		this.mappedTo = mappedTo;
	}

	public MappedBy getReferred() {
		return referred;
	}

	public void setReferred(MappedBy referred) {
		this.referred = referred;
	}

	public void setMappedBy(String mappedBy) {
		this.mappedBy = mappedBy;
	}

	
}
