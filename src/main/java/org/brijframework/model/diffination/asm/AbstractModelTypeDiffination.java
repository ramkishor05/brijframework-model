package org.brijframework.model.diffination.asm;

import java.util.HashMap;
import java.util.Map;

import org.brijframework.model.diffination.ModelConstructorDiffination;
import org.brijframework.model.diffination.ModelPropertyDiffinationGroup;
import org.brijframework.model.diffination.ModelTypeDiffination;

public abstract class AbstractModelTypeDiffination extends AbstractModelDiffination<Class<?>> implements ModelTypeDiffination{
	
	private Map<String,ModelPropertyDiffinationGroup>  properties;
	private Class<?> type;
	private ModelTypeDiffination owner;
	private ModelConstructorDiffination constructor;
	
	public void setConstructor(ModelConstructorDiffination constructor) {
		this.constructor = constructor;
	}
	
	public void setType(Class<?> type) {
		this.type = type;
	}
	
	@Override
	public Class<?> getType() {
		return type;
	}

	@Override
	public Map<String, ModelPropertyDiffinationGroup> getProperties() {
		if(properties==null) {
			properties=new HashMap<String, ModelPropertyDiffinationGroup>();
		}
		return properties;
	}
	
	@Override
	public ModelTypeDiffination getOwner() {
		return owner;
	}

	@Override
	public void setOwner(ModelTypeDiffination owner) {
		this.owner=owner;
	}
	@Override
	public ModelPropertyDiffinationGroup getProperty(String _key) {
		return getProperties().get(_key);
	}
	@Override
	public ModelConstructorDiffination getConstructor() {
		return constructor;
	}
}
