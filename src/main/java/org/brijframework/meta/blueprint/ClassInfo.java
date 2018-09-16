package org.brijframework.meta.blueprint;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.brijframework.meta.MetaInfo;
import org.brijframework.meta.accessor.ClassAccessor;
import org.brijframework.meta.properties.FieldInfo;
import org.brijframework.meta.relations.RelInfo;

public interface ClassInfo extends MetaInfo {

	public String getBean();

	public String getType();

	public boolean isUseDefaultFields();

	public boolean isUseDefaultMethods();

	public Set<String> getMethodKeys();

	public Set<String> getPropertyKeys();

	public Set<String> getReferKeys();

	@SuppressWarnings("unchecked")
	public Class<?> getTarget();

	public void papulate(Map<String, Object> map);

	public void setAccessor(ClassAccessor accessor);

	public ClassAccessor getAccessor();

	public String getParentID();
	
	public FieldInfo getPropertyInfo(String _key);

	public ConstructInfo getConstructor();

	public ConstructInfo getConstructorInfo(Type... types);

	public Collection<? extends ConstructInfo> getConstructors();

	public Collection<? extends FieldInfo> getPropertiesInfo();

	public Collection<? extends RelInfo>  getRelationInfoList();

	
}
