package org.brijframework.meta.reflect;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import org.brijframework.meta.MetaInfo;

public interface MethodMetaInfo extends MetaInfo{
	
	@Override
	public default void init() {
		setReturnType(getTarget().getReturnType());
	}
	
	public void setReturnType(Class<?> type);
	
	public Class<?> getReturnType();
	
	@SuppressWarnings("unchecked")
	public Method getTarget();

	public void setArguments(Type[] arguments);
	
	public Type[] getArguments();
	
	public Set<ParamMetaInfo> getParametersInfo();

	public void papulate(Map<String, Object> annotationData);

	public String getParentID();
	
	public default Object[] getParamValues() {
		Set<ParamMetaInfo> params= getParametersInfo();
		if(params==null) {
			return null;
		}
		Object[] values=new Object[params.size()];
		AtomicInteger count=new AtomicInteger(0);
		params.stream().sorted().forEach(paramInfo -> {
			values[count.getAndIncrement()]=paramInfo.getValue();
		});
		return values;
	}

	ClassMetaInfo getClassMetaInfo();
}
