package org.brijframework.model.info;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;

import org.brijframework.model.ModelMetaData;

public interface ConstructorModelMetaData extends ModelMetaData<Constructor<?>> {

	@Override
	public Constructor<?> getTarget();

	public void papulate(Map<String, Object> map);

	public Set<ParameterModelMetaData<?>> getParameters();
	
	public void setParameters(Set<ParameterModelMetaData<?>> params);

	public void setArguments(Type[] arguments);

	public Type[] getArguments();
	
	public Object[] getValues();
	
	public default int getParameterCount() {
		if (getParameters() == null || getParameters().isEmpty()) {
			return 0;
		}
		return getParameters().size();
	}

	public ClassModelMetaData getOwner();

}
