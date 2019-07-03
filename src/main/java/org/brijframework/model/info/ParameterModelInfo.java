package org.brijframework.model.info;

import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.Map;

import org.brijframework.model.ModelInfo;

public interface ParameterModelInfo  extends Comparable<ParameterModelInfo> , ModelInfo<Parameter>{

	public int getIndex() ;
	
	public Type getType() ;
	
	public Object getValue();
	
	public void validate();
	
	public void papulate(Map<String, Object> map);
	
	@Override
	default int compareTo(ParameterModelInfo param) {
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
