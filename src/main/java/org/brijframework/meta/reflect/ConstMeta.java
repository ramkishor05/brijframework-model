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

	public Set<ParamMeta> getParametersInfo();
	
	public void setParametersInfo(Set<ParamMeta> params);

	public void setArguments(Type[] arguments);

	public Type[] getArguments();
	
	public Object[] getValues();
	
	public default int getParameterCount() {
		if (getParametersInfo() == null || getParametersInfo().isEmpty()) {
			return 0;
		}
		return getParametersInfo().size();
	}

	public ClassMeta getOwner();;


}
