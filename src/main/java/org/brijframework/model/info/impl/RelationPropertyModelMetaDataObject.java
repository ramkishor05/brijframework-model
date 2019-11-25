package org.brijframework.model.info.impl;

import java.lang.reflect.AccessibleObject;

import org.brijframework.model.info.ClassModelMetaData;
import org.brijframework.support.enums.MappedBy;

public class RelationPropertyModelMetaDataObject extends PropertyModelMetaDataObject implements org.brijframework.model.info.RelationPropertyModelMetaData{

	Class<?> mappedTo;
	
	String mappedBy;
	
	MappedBy referred;
	
	public RelationPropertyModelMetaDataObject(AccessibleObject target) {
		super(target);
	}
	
	public RelationPropertyModelMetaDataObject(ClassModelMetaData owner, AccessibleObject target) {
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
	public ClassModelMetaData getTargetClassMetaInfo() {
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
