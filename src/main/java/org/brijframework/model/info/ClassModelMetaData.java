package org.brijframework.model.info;

import java.util.Map;

import org.brijframework.model.ModelMetaData;

public interface ClassModelMetaData extends ModelMetaData<Class<?>> {

	public String getType();

	public Class<?> getTarget();

	public ClassModelMetaData getOwner();
	
	public void setOwner(ClassModelMetaData owner);
	
	public PropertyModelMetaDataGroup getProperty(String _key);

	public ConstructorModelMetaData getConstructor();

	public Map<String, PropertyModelMetaDataGroup> getProperties();
	
	

}
