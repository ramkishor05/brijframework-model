package org.brijframework.model.info.asm;

import java.lang.reflect.AccessibleObject;
import java.util.HashMap;
import java.util.Map;

import org.brijframework.model.info.ClassModelMetaData;
import org.brijframework.model.info.PropertyModelMetaData;

public abstract class AbstractPropertyModelMetaData extends AbstractModelMetaData<AccessibleObject> implements PropertyModelMetaData {

	private AccessibleObject target;
	private ClassModelMetaData owner;
	private Object value;
	public boolean required;
	private Class<?> type;
	private Map<String,String> mapper;
	
	@Override
	public ClassModelMetaData getOwner() {
		return owner;
	}
	
	public void setOwner(ClassModelMetaData owner) {
		this.owner = owner;
	}
	
	public void setValue(Object value) {
		this.value = value;
	}

	@Override
	public Object getValue() {
		return value;
	}
	
	public void setTarget(AccessibleObject target) {
		this.target = target;
	}
	
	@Override
	public AccessibleObject getTarget() {
		return target;
	}
	
	
	public boolean isRequired() {
		return required;
	}
	
	public void setRequired(boolean required) {
		this.required = required;
	}
	

	public void setType(Class<?> type) {
		this.type = type;
	}
	
	public Class<?> getType() {
		return type;
	}
	
	public void setMapper(Map<String, String> mapper) {
		this.mapper = mapper;
	}
	
	public Map<String, String> getMapper() {
		if(mapper==null) {
			mapper=new HashMap<String, String>();
		}
		return mapper;
	}

}
