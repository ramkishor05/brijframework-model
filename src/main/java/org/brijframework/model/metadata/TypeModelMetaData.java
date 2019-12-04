package org.brijframework.model.metadata;

import java.util.Map;

import org.brijframework.model.ModelMetaData;

public interface TypeModelMetaData extends ModelMetaData<Class<?>> {

	public Class<?> getType();

	public TypeModelMetaData getOwner();
	
	public void setOwner(TypeModelMetaData owner);
	
	public PropertyModelMetaDataGroup getProperty(String _key);

	public ConstructorModelMetaData getConstructor();

	public Map<String, PropertyModelMetaDataGroup> getProperties();

}
