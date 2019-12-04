package org.brijframework.model.metadata;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import org.brijframework.model.ModelMetaData;

public interface MethodModelMetaData extends ModelMetaData<Method>{
	
	@Override
	public default void init() {
		setReturnType(getType().getReturnType());
	}
	
	public void setReturnType(Class<?> type);
	
	public Class<?> getReturnType();
	
	public Method getType();

	public void setArguments(Type[] arguments);
	
	public Type[] getArguments();
	
	public Set<ParameterModelMetaData<?>> getParameters();

	public void papulate(Map<String, Object> annotationData);

	public String getParentID();
	
	public default Object[] getParamValues() {
		Set<ParameterModelMetaData<?>> params= getParameters();
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

	TypeModelMetaData getClassMetaInfo();
}
