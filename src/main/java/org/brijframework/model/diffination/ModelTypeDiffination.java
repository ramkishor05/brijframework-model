package org.brijframework.model.diffination;

import java.util.Map;

import org.brijframework.model.ModelDiffination;

public interface ModelTypeDiffination extends ModelDiffination<Class<?>> {

	public Class<?> getType();

	public ModelTypeDiffination getOwner();
	
	public void setOwner(ModelTypeDiffination owner);
	
	public ModelPropertyDiffinationGroup getProperty(String _key);

	public ModelConstructorDiffination getConstructor();

	public Map<String, ModelPropertyDiffinationGroup> getProperties();

}
