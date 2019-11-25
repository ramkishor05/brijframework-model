package org.brijframework.model.info.asm;

import java.util.HashMap;
import java.util.Map;

import org.brijframework.model.info.ClassModelMetaData;
import org.brijframework.model.info.ConstructorModelMetaData;
import org.brijframework.model.info.PropertyModelMetaDataGroup;

public abstract class AbstractClassModelMetaData extends AbstractModelMetaData<Class<?>> implements ClassModelMetaData{
	
	private Map<String,PropertyModelMetaDataGroup>  properties;
	private Class<?> target;
	private String type;
	private ClassModelMetaData owner;
	private ConstructorModelMetaData constructor;
	
	public void setConstructor(ConstructorModelMetaData constructor) {
		this.constructor = constructor;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String getType() {
		return type;
	}
	
	public void setTarget(Class<?> target) {
		this.target = target;
	}

	@Override
	public Class<?> getTarget() {
		return target;
	}

	@Override
	public Map<String, PropertyModelMetaDataGroup> getProperties() {
		if(properties==null) {
			properties=new HashMap<String, PropertyModelMetaDataGroup>();
		}
		return properties;
	}
	
	@Override
	public ClassModelMetaData getOwner() {
		return owner;
	}

	@Override
	public void setOwner(ClassModelMetaData owner) {
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
