package org.brijframework.model.diffination.impl;

import java.lang.reflect.AccessibleObject;

import org.brijframework.model.diffination.TypeModelDiffination;
import org.brijframework.support.enums.MappedBy;

public class RelationPropertyModelMetaDataObject extends PropertyModelMetaDataObject implements org.brijframework.model.diffination.RelationPropertyModelMetaData{

	Class<?> mappedTo;
	
	String mappedBy;
	
	MappedBy referred;
	
	public RelationPropertyModelMetaDataObject(AccessibleObject target) {
		super(target);
	}
	
	public RelationPropertyModelMetaDataObject(TypeModelDiffination owner, AccessibleObject target) {
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
	public TypeModelDiffination getTargetClassMetaInfo() {
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
