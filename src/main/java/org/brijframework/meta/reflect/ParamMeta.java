package org.brijframework.meta.reflect;

import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.Map;

import org.brijframework.meta.MetaInfo;

public interface ParamMeta  extends Comparable<ParamMeta> , MetaInfo<Parameter>{

	public int getIndex() ;
	
	public Type getType() ;
	
	public Object getValue();
	
	public void validate();
	
	public void papulate(Map<String, Object> map);
	
	@Override
	default int compareTo(ParamMeta param) {
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
