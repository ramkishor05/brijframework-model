package org.brijframework.model.resource;

import org.brijframework.model.ModelResource;

public interface ParameterModelResource  extends Comparable<ParameterModelResource> , ModelResource<String>{

	public int getIndex() ;
	
	public String getType() ;
	
	@Override
	default int compareTo(ParameterModelResource param) {
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
