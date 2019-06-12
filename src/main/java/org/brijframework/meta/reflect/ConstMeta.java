package org.brijframework.meta.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;

import org.brijframework.meta.MetaInfo;

public interface ConstMeta extends MetaInfo<Constructor<?>> {

	@Override
	public Constructor<?> getTarget();

	public void papulate(Map<String, Object> map);

	public Set<ParamMeta> getParams();
	
	public void setParametersInfo(Set<ParamMeta> params);

	public void setArguments(Type[] arguments);

	public Type[] getArguments();
	
	public Object[] getValues();
	
	public default int getParameterCount() {
		if (getParams() == null || getParams().isEmpty()) {
			return 0;
		}
		return getParams().size();
	}

	public ClassMeta getOwner();

}
