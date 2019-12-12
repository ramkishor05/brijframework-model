package org.brijframework.model.diffination;

import java.util.Map;

import org.brijframework.model.ModelDiffination;

public interface TypeModelDiffination extends ModelDiffination<Class<?>> {

	public Class<?> getType();

	public TypeModelDiffination getOwner();
	
	public void setOwner(TypeModelDiffination owner);
	
	public PropertyModelMetaDataGroup getProperty(String _key);

	public ConstructorModelMetaData getConstructor();

	public Map<String, PropertyModelMetaDataGroup> getProperties();

}
