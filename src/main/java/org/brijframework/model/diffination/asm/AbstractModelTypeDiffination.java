package org.brijframework.model.diffination.asm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.brijframework.Access;
import org.brijframework.model.diffination.ModelConstructorDiffination;
import org.brijframework.model.diffination.ModelPropertyDiffinationGroup;
import org.brijframework.model.diffination.ModelTypeDeffination;

public abstract class AbstractModelTypeDiffination extends AbstractModelDiffination<Class<?>> implements ModelTypeDeffination{
	
	private Map<String,ModelPropertyDiffinationGroup>  properties;
	private Class<?> type;
	private ModelTypeDeffination owner;
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
	public ModelTypeDeffination getOwner() {
		return owner;
	}

	@Override
	public void setOwner(ModelTypeDeffination owner) {
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
	
	@Override
	public Set<String> getPropertiesNames(Access...accesses){
		HashSet<String> propertiesNames = new HashSet<String>();
		for (Entry<String, ModelPropertyDiffinationGroup> entry :  getProperties().entrySet()) {
			for(Access access: accesses) {
				if(entry.getValue().getGetterMeta()!=null && access.equals(entry.getValue().getGetterMeta().getAccess())) {
					propertiesNames.add(entry.getKey());
				}
			}
		}
		return propertiesNames;
	}
}
