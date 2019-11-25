package org.brijframework.model.resource;

import java.lang.reflect.Type;
import java.util.Map;

import org.brijframework.model.ModelResource;

public interface ParameterModelResource<T>  extends Comparable<ParameterModelResource<T>> , ModelResource<T>{

	public int getIndex() ;
	
	public Type getType() ;
	
	public Object getValue();
	
	public void validate();
	
	public void papulate(Map<String, Object> map);
	
	@Override
	default int compareTo(ParameterModelResource<T> param) {
		if(param.getIndex()<getIndex()) {
			return 1;
		}else {
			if(param.getIndex()>getIndex()) {
				return -1;
			}else {
				return -0;
			}
		}
	}
	
}
