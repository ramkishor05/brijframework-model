package org.brijframework.model.info;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;

import org.brijframework.model.ModelInfo;
import org.brijframework.model.setup.ParamMetaSetup;

public interface ConstModelInfo extends ModelInfo<Constructor<?>> {

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

	public OwnerModelInfo getOwner();

}
