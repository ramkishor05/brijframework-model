package org.brijframework.model.metadata;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.brijframework.model.ModelMetaData;

public interface PropertyModelMetaData extends ModelMetaData<AccessibleObject>{

	public void setValue(Object value);
	
	public Object getValue();

	public AccessibleObject getType();

	default boolean isField() {
		return getType() instanceof Field ;
	}

	default boolean isGetter() {
		return getType() instanceof Method && ((Method) getType()).getParameterCount()==0 && ((Method) getType()).getName().startsWith("get");
	}

	default boolean isSetter() {
		return getType() instanceof Method && ((Method) getType()).getParameterCount()==1 && ((Method) getType()).getName().startsWith("set");
	}
	
	default Method getTargetAsMethod() {
		return (Method)getType();
	}
	
	default Field getTargetAsField() {
		return (Field)getType();
	}

	public TypeModelMetaData getOwner();

}
