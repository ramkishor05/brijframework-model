package org.brijframework.meta;

import java.lang.reflect.Type;

import org.brijframework.lifecycle.Initializer;
import org.brijframework.meta.reflect.ClassMeta;
import org.brijframework.util.reflect.ParamUtil;

public interface KeyInfo extends Initializer{

	String getId();
	
	String getName();

	<T> T getTarget();
	
	Type[] getParams();
	
	ClassMeta getOwner();
	
	default boolean isDefultParam(String key) {
		return this.getName().equals(key)&&( this.getParams()==null || this.getParams().length==0);
	}
	
	default boolean isFilterParam(Type[] _agruments) {
		return ParamUtil.isEqualTypes(this.getParams(), _agruments);
	}
	
	
	default boolean isFilterParam(String key, Type[] _agruments) {
		if(!this.getName().equals(key)) {
			return false;
		}
		return ParamUtil.isEqualTypes(this.getParams(), _agruments);
	}
	
	default boolean isFilterParam(Class<?>parent,String key, Type[] _agruments) {
		if(!this.getOwner().getTarget().equals(parent)) {
			return false;
		}
		return isFilterParam(key, _agruments);
	}
	
	default boolean isFilterParam(String id,String key, Type[] _agruments) {
		if(!this.getId().equals(id)) {
			return false;
		}
		return isFilterParam(key, _agruments);
	}
	

	default boolean isFilterParam(Class<?>parent,String mdlID,String key, Type[] _agruments) {
		if(!this.getOwner().getTarget().equals(parent)) {
			return false;
		}
		return isFilterParam(mdlID,key, _agruments);
	}

}
