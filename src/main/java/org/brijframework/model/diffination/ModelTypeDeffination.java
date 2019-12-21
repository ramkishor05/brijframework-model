package org.brijframework.model.diffination;

import java.util.Map;

import org.brijframework.model.ModelDiffination;

public interface ModelTypeDeffination extends ModelDiffination<Class<?>> {

	public Class<?> getType();

	public ModelTypeDeffination getOwner();
	
	public void setOwner(ModelTypeDeffination owner);
	
	public ModelPropertyDiffinationGroup getProperty(String _key);

	public ModelConstructorDiffination getConstructor();

	public Map<String, ModelPropertyDiffinationGroup> getProperties();

}
