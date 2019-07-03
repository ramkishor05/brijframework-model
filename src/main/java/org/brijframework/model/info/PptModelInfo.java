package org.brijframework.model.info;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.brijframework.model.ModelInfo;

public interface PptModelInfo extends ModelInfo<AccessibleObject>{

	public void setValue(Object value);
	
	public Object getValue();

	public AccessibleObject getTarget();

	default boolean isField() {
		return getTarget() instanceof Field ;
	}

	default boolean isGetter() {
		return getTarget() instanceof Method && ((Method) getTarget()).getParameterCount()==0 && ((Method) getTarget()).getName().startsWith("get");
	}

	default boolean isSetter() {
		return getTarget() instanceof Method && ((Method) getTarget()).getParameterCount()==1 && ((Method) getTarget()).getName().startsWith("set");
	}
	
	default Method getTargetAsMethod() {
		return (Method)getTarget();
	}
	
	default Field getTargetAsField() {
		return (Field)getTarget();
	}

	public OwnerModelInfo getOwner();

	public Class<?> getType();

}
