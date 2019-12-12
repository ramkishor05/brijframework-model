package org.brijframework.model.diffination.asm;

import java.lang.reflect.AccessibleObject;
import java.util.HashMap;
import java.util.Map;

import org.brijframework.model.diffination.PropertyModelMetaData;
import org.brijframework.model.diffination.TypeModelDiffination;

public abstract class AbstractPropertyModelMetaData extends AbstractModelMetaData<AccessibleObject> implements PropertyModelMetaData {

	private AccessibleObject type;
	private TypeModelDiffination owner;
	private Object value;
	public boolean required;
	private Map<String,String> mapper;
	
	@Override
	public TypeModelDiffination getOwner() {
		return owner;
	}
	
	public void setOwner(TypeModelDiffination owner) {
		this.owner = owner;
	}
	
	public void setValue(Object value) {
		this.value = value;
	}

	@Override
	public Object getValue() {
		return value;
	}
	
	public void setType(AccessibleObject type) {
		this.type = type;
	}
	
	@Override
	public AccessibleObject getType() {
		return type;
	}
	
	
	public boolean isRequired() {
		return required;
	}
	
	public void setRequired(boolean required) {
		this.required = required;
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
