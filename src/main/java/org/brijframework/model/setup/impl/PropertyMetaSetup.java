package org.brijframework.model.setup.impl;

import org.brijframework.model.setup.FieldMetaSetup;

public class PropertyMetaSetup implements FieldMetaSetup{

	private String id;
	private String name;
	private String target;
	private String type;
	private String access;
	private Object value;
	private String[] mappedKeys;
	private boolean required;
	
	@Override
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	
	@Override
	public String getAccess() {
		return access;
	}
	
	public void setAccess(String access) {
		this.access = access;
	}
	
	public String[] getMappedKeys() {
		return mappedKeys;
	}
	public void setMappedKeys(String[] mappedKeys) {
		this.mappedKeys = mappedKeys;
	}
	
	@Override
	public boolean isRequired() {
		return required;
	}
	
	public void setRequired(boolean isRequired) {
		this.required = isRequired;
	}
	
	public void setValue(Object value) {
		this.value = value;
	}
	
	@Override
	public Object getValue() {
		return value;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String getType() {
		return type;
	}
}
