package org.brijframework.meta.abstraction;

import java.util.Set;

import org.brijframework.meta.KeyInfo;
import org.brijframework.meta.reflect.ClassMetaInfo;

public abstract class AbstractClassMetaInfo extends AbstractMetaInfo implements ClassMetaInfo{
	
	private Class<?> parent;
	private Class<?> target;
	private KeyInfo keyInfo ;
	private String type;
	private boolean isUseDefaultField;
	private boolean isUseDefaultMethod;
	private Set<String> methodKeys;
	private Set<String> propertyKeys;
	private Set<String> relationKeys;
	
	public void setParent(Class<?> parent) {
		this.parent=parent;
	}
	
	@Override
	public Class<?> getParent() {
		return parent;
	}
	
	public void setKeyInfo(KeyInfo keyInfo) {
		this.keyInfo = keyInfo;
	}

	@Override
	public KeyInfo getKeyInfo() {
		return keyInfo;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String getType() {
		return type;
	}
	
	public void setUseDefaultField(boolean isUseDefaultField) {
		this.isUseDefaultField = isUseDefaultField;
	}

	@Override
	public boolean isUseDefaultFields() {
		return isUseDefaultField;
	}
	
	public void setUseDefaultMethod(boolean isUseDefaultMethod) {
		this.isUseDefaultMethod = isUseDefaultMethod;
	}

	@Override
	public boolean isUseDefaultMethods() {
		return isUseDefaultMethod;
	}
	
	public void setMethodKeys(Set<String> methodKeys) {
		this.methodKeys = methodKeys;
	}

	@Override
	public Set<String> getMethodKeys() {
		return methodKeys;
	}

	public void setPropertyKeys(Set<String> propertyKeys) {
		this.propertyKeys = propertyKeys;
	}
	
	@Override
	public Set<String> getPropertyKeys() {
		return propertyKeys;
	}
	
	public void setRelationKeys(Set<String> relationKeys) {
		this.relationKeys = relationKeys;
	}

	@Override
	public Set<String> getRelationKeys() {
		return relationKeys;
	}
	
	public void setTarget(Class<?> target) {
		this.target = target;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Class<?> getTarget() {
		return target;
	}

}
