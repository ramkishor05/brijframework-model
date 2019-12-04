package org.brijframework.model.metadata.impl;

import java.lang.reflect.AccessibleObject;

import org.brijframework.model.metadata.TypeModelMetaData;
import org.brijframework.support.enums.MappedBy;

public class RelationPropertyModelMetaDataObject extends PropertyModelMetaDataObject implements org.brijframework.model.metadata.RelationPropertyModelMetaData{

	Class<?> mappedTo;
	
	String mappedBy;
	
	MappedBy referred;
	
	public RelationPropertyModelMetaDataObject(AccessibleObject target) {
		super(target);
	}
	
	public RelationPropertyModelMetaDataObject(TypeModelMetaData owner, AccessibleObject target) {
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
	public TypeModelMetaData getTargetClassMetaInfo() {
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
