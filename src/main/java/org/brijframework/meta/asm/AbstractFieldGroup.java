package org.brijframework.meta.asm;

import org.brijframework.meta.reflect.ClassMeta;
import org.brijframework.meta.reflect.FieldGroup;
import org.brijframework.meta.reflect.FieldMeta;

public abstract class AbstractFieldGroup implements FieldGroup {
	
	private String id;
	private String name;
	private FieldMeta fieldMeta;
	private FieldMeta setterMeta;
	private FieldMeta getterMeta;
	private ClassMeta owner;
	
	public AbstractFieldGroup(ClassMeta owner) {
		this.owner=owner;
	}
	
	@Override
	public ClassMeta getOwner() {
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
	public FieldMeta getFieldMeta() {
		return fieldMeta;
	}
	
	public void setFieldMeta(FieldMeta fieldMeta) {
		this.fieldMeta = fieldMeta;
	}

	@Override
	public FieldMeta getSetterMeta() {
		return setterMeta;
	}
	
	public void setSetterMeta(FieldMeta setterMeta) {
		this.setterMeta = setterMeta;
	}

	@Override
	public FieldMeta getGetterMeta() {
		return getterMeta;
	}
	
	public void setGetterMeta(FieldMeta getterMeta) {
		this.getterMeta = getterMeta;
	}

}
