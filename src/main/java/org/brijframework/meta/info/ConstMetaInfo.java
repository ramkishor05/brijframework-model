package org.brijframework.meta.info;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;

import org.brijframework.meta.MetaInfo;
import org.brijframework.meta.setup.ParamMetaSetup;

public interface ConstMetaInfo extends MetaInfo<Constructor<?>> {

	@Override
	public Constructor<?> getTarget();

	public void papulate(Map<String, Object> map);

	public Set<ParamMetaSetup> getParams();
	
	public void setParametersInfo(Set<ParamMetaSetup> params);

	public void setArguments(Type[] arguments);

	public Type[] getArguments();
	
	public Object[] getValues();
	
	public default int getParameterCount() {
		if (getParams() == null || getParams().isEmpty()) {
			return 0;
		}
		return getParams().size();
	}

	public ClassMetaInfo getOwner();

}
