package org.brijframework.model.diffination;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;

import org.brijframework.model.ModelDiffination;

public interface ConstructorModelMetaData extends ModelDiffination<Constructor<?>> {

	@Override
	public Constructor<?> getType();

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

	public TypeModelDiffination getOwner();

}
