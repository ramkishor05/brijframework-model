package org.brijframework.meta.reflect;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

import org.brijframework.meta.MetaInfo;

public interface FieldMeta extends MetaInfo<AccessibleObject>{

	public void setValue(Object value);
	
	public Object getValue();

	public AccessibleObject getTarget();

	public void papulate(Map<String, Object> properties);

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

	public ClassMeta getOwner();

}
