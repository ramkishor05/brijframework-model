package org.brijframework.model.resource;

import java.lang.reflect.Type;
import java.util.Map;

import org.brijframework.model.ModelResource;

public interface ParameterModelResource<T extends Type>  extends Comparable<ParameterModelResource<Type>> , ModelResource<Type>{

	public int getIndex() ;
	
	public T getType() ;
	
	public Object getValue();
	
	public void validate();
	
	public void papulate(Map<String, Object> map);
	
	@Override
	default int compareTo(ParameterModelResource<Type> param) {
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
