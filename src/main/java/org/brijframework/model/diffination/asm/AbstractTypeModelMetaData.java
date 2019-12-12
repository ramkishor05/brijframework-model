package org.brijframework.model.diffination.asm;

import java.util.HashMap;
import java.util.Map;

import org.brijframework.model.diffination.ConstructorModelMetaData;
import org.brijframework.model.diffination.PropertyModelMetaDataGroup;
import org.brijframework.model.diffination.TypeModelDiffination;

public abstract class AbstractTypeModelMetaData extends AbstractModelMetaData<Class<?>> implements TypeModelDiffination{
	
	private Map<String,PropertyModelMetaDataGroup>  properties;
	private Class<?> type;
	private TypeModelDiffination owner;
	private ConstructorModelMetaData constructor;
	
	public void setConstructor(ConstructorModelMetaData constructor) {
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
	public Map<String, PropertyModelMetaDataGroup> getProperties() {
		if(properties==null) {
			properties=new HashMap<String, PropertyModelMetaDataGroup>();
		}
		return properties;
	}
	
	@Override
	public TypeModelDiffination getOwner() {
		return owner;
	}

	@Override
	public void setOwner(TypeModelDiffination owner) {
		this.owner=owner;
	}
	@Override
	public PropertyModelMetaDataGroup getProperty(String _key) {
		return getProperties().get(_key);
	}
	@Override
	public ConstructorModelMetaData getConstructor() {
		return constructor;
	}
}
