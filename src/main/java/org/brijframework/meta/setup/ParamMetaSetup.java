package org.brijframework.meta.setup;

import java.lang.reflect.Parameter;
import java.lang.reflect.Type;

import org.brijframework.meta.MetaInfo;

public interface ParamMetaSetup  extends Comparable<ParamMetaSetup> , MetaInfo<Parameter>{

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
