package org.brijframework.meta.properties;

import java.lang.reflect.Type;
import java.util.Map;

public interface ParamInfo  extends Comparable<ParamInfo>{

	public int getIndex() ;
	
	public String getName() ;
	
	public Type getType() ;
	
	public Object getValue();
	
	public void validate();
	
	public void papulate(Map<String, Object> map);
	
	@Override
	default int compareTo(ParamInfo param) {
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
