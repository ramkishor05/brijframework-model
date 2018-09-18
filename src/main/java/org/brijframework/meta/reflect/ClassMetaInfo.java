package org.brijframework.meta.reflect;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.brijframework.meta.MetaInfo;

public interface ClassMetaInfo extends MetaInfo {

	public String getType();

	public boolean isUseDefaultFields();

	public boolean isUseDefaultMethods();

	public Set<String> getMethodKeys();

	public Set<String> getPropertyKeys();

	public Set<String> getRelationKeys();

	@SuppressWarnings("unchecked")
	@Override
	public Class<?> getTarget();

	public void papulate(Map<String, Object> map);

	public String getParentID();
	
	public FieldMetaInfo getPropertyInfo(String _key);

	public ConsMetaInfo getConstructor();

	public ConsMetaInfo getConstructorInfo(Type... types);

	public Collection<? extends ConsMetaInfo> getConstructors();

	public Collection<? extends FieldMetaInfo> getPropertiesInfo();

	public Collection<? extends RelationMetaInfo>  getRelationInfoList();

	
}
