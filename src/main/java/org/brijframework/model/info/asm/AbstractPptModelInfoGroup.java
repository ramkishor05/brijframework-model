package org.brijframework.model.info.asm;

import org.brijframework.model.info.OwnerModelInfo;
import org.brijframework.model.info.PptModelInfoGroup;
import org.brijframework.model.info.PptModelInfo;

public abstract class AbstractPptModelInfoGroup implements PptModelInfoGroup {
	
	private String id;
	private String name;
	private PptModelInfo fieldMeta;
	private PptModelInfo setterMeta;
	private PptModelInfo getterMeta;
	private OwnerModelInfo owner;
	
	public AbstractPptModelInfoGroup(OwnerModelInfo owner) {
		this.owner=owner;
	}
	
	@Override
	public OwnerModelInfo getOwner() {
		return owner;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public String getId() {
		return this.id ;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String getName() {
		return this.name;
	}
	
	
	@Override
	public PptModelInfo getFieldMeta() {
		return fieldMeta;
	}
	
	public void setFieldMeta(PptModelInfo fieldMeta) {
		this.fieldMeta = fieldMeta;
	}

	@Override
	public PptModelInfo getSetterMeta() {
		return setterMeta;
	}
	
	public void setSetterMeta(PptModelInfo setterMeta) {
		this.setterMeta = setterMeta;
	}

	@Override
	public PptModelInfo getGetterMeta() {
		return getterMeta;
	}
	
	public void setGetterMeta(PptModelInfo getterMeta) {
		this.getterMeta = getterMeta;
	}

}
