package org.brijframework.model.setup;

import java.lang.reflect.Type;

import org.brijframework.model.ModelInfo;

public interface ParamMetaSetup  extends Comparable<ParamMetaSetup> , ModelInfo<String>{

	public int getIndex() ;
	
	public Type getType() ;
	
	public Object getValue();
	
	public void validate();
	
	@Override
	default int compareTo(ParamMetaSetup param) {
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
