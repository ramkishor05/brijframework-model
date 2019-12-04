package org.brijframework.model.metadata.asm;

import java.util.HashMap;
import java.util.Map;

import org.brijframework.model.metadata.ConstructorModelMetaData;
import org.brijframework.model.metadata.PropertyModelMetaDataGroup;
import org.brijframework.model.metadata.TypeModelMetaData;

public abstract class AbstractTypeModelMetaData extends AbstractModelMetaData<Class<?>> implements TypeModelMetaData{
	
	private Map<String,PropertyModelMetaDataGroup>  properties;
	private Class<?> type;
	private TypeModelMetaData owner;
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
	public TypeModelMetaData getOwner() {
		return owner;
	}

	@Override
	public void setOwner(TypeModelMetaData owner) {
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
