package org.brijframework.model.diffination.asm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.brijframework.Access;
import org.brijframework.model.diffination.ModelConstructorDiffination;
import org.brijframework.model.diffination.ModelPropertyDiffinationGroup;
import org.brijframework.model.diffination.ModelTypeDeffination;
import org.brijframework.util.support.ReflectionAccess;

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
	public Set<String> getPropertiesGetterNames(Access...accesses){
		HashSet<String> propertiesNames = new HashSet<String>();
		for (Entry<String, ModelPropertyDiffinationGroup> entry :  getProperties().entrySet()) {
			if(entry.getValue().getGetterMeta() != null && ReflectionAccess.PUBLIC.isAccess(entry.getValue().getGetterMeta().getGetterModifiers())) {
				propertiesNames.add(entry.getKey());
			} else if(entry.getValue().getFieldMeta() != null && ReflectionAccess.PUBLIC.isAccess(entry.getValue().getFieldMeta().getGetterModifiers())) {
				propertiesNames.add(entry.getKey());
			}else {
				for(Access access: accesses) {
					if(access.equals(entry.getValue().getAccess())) {
						propertiesNames.add(entry.getKey());
					}
				}
			}
		}
		return propertiesNames;
	}
	
	@Override
	public Set<String> getPropertiesSetterNames(Access...accesses){
		HashSet<String> propertiesNames = new HashSet<String>();
		for (Entry<String, ModelPropertyDiffinationGroup> entry :  getProperties().entrySet()) {
			if(entry.getValue().getSetterMeta() != null && ReflectionAccess.PUBLIC.isAccess(entry.getValue().getSetterMeta().getSetterModifiers())) {
				propertiesNames.add(entry.getKey());
			} else if(entry.getValue().getFieldMeta() != null && ReflectionAccess.PUBLIC.isAccess(entry.getValue().getFieldMeta().getSetterModifiers())) {
				propertiesNames.add(entry.getKey());
			}else {
				for(Access access: accesses) {
					if(access.equals(entry.getValue().getAccess())) {
						propertiesNames.add(entry.getKey());
					}
				}
			}
		}
		return propertiesNames;
	}
}
