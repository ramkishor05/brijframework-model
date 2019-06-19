package org.brijframework.meta.asm;

import org.brijframework.meta.info.ClassMetaInfo;
import org.brijframework.meta.info.FieldGroup;
import org.brijframework.meta.info.FieldMetaInfo;

public abstract class AbstractFieldGroup implements FieldGroup {
	
	private String id;
	private String name;
	private FieldMetaInfo fieldMeta;
	private FieldMetaInfo setterMeta;
	private FieldMetaInfo getterMeta;
	private ClassMetaInfo owner;
	
	public AbstractFieldGroup(ClassMetaInfo owner) {
		this.owner=owner;
	}
	
	@Override
	public ClassMetaInfo getOwner() {
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
	public FieldMetaInfo getFieldMeta() {
		return fieldMeta;
	}
	
	public void setFieldMeta(FieldMetaInfo fieldMeta) {
		this.fieldMeta = fieldMeta;
	}

	@Override
	public FieldMetaInfo getSetterMeta() {
		return setterMeta;
	}
	
	public void setSetterMeta(FieldMetaInfo setterMeta) {
		this.setterMeta = setterMeta;
	}

	@Override
	public FieldMetaInfo getGetterMeta() {
		return getterMeta;
	}
	
	public void setGetterMeta(FieldMetaInfo getterMeta) {
		this.getterMeta = getterMeta;
	}

}
