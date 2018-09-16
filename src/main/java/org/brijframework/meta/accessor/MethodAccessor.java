package org.brijframework.meta.accessor;

import java.lang.reflect.Type;

import org.brijframework.accessor.Accessor;
import org.brijframework.meta.factories.MethodInfoFactory;
import org.brijframework.meta.properties.MethodInfo;

public interface MethodAccessor extends Accessor {

	public MethodInfo getMethodInfo(Type...types);
	
	public Object[] getParamValues(MethodInfo info,Object... _values); 
	
	public Object call(String id,Object _instance, Object... _values);

	public String getParentID();
	
	public MethodInfoFactory getInfoFactory();

}
