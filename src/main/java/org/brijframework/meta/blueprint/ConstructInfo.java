package org.brijframework.meta.blueprint;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;

import org.brijframework.meta.MetaInfo;
import org.brijframework.meta.properties.ParamInfo;
import org.brijframework.util.casting.CastingUtil;

public interface ConstructInfo extends MetaInfo {

	@SuppressWarnings("unchecked")
	@Override
	public Constructor<?> getTarget();

	void papulate(Map<String, Object> map);

	Set<ParamInfo> getParametersInfo();
	
	public void setParametersInfo(Set<ParamInfo> params);

	public void setArguments(Type[] arguments);

	public Type[] getArguments();
	
	public default Object[] getParameterValues() {
		Object[] values = new Object[getParameterCount()];
		getParametersInfo().stream().sorted((p1,p2)->p1.compareTo(p2)).forEach(paramInfo -> {
			values[paramInfo.getIndex()] = CastingUtil.castObject(paramInfo.getValue(), paramInfo.getType());
		});
		return values;
	}
	
	public default int getParameterCount() {
		if (getParametersInfo() == null || getParametersInfo().isEmpty()) {
			return 0;
		}
		return getParametersInfo().size();
	};


}
