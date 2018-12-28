package org.brijframework.meta.asm;

import java.util.Set;

import org.brijframework.meta.KeyInfo;
import org.brijframework.meta.reflect.ClassMetaInfo;

public abstract class AbstractClassMetaInfo extends AbstractMetaInfo implements ClassMetaInfo{
	
	private ClassMetaInfo owner;
	private Class<?> target;
	private KeyInfo keyInfo ;
	private String type;
	private Set<String> methodKeys;
	private Set<String> propertyKeys;
	private Set<String> relationKeys;
	
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
	
	public void setOwner(ClassMetaInfo owner) {
		this.owner = owner;
	}
	
	@Override
	public ClassMetaInfo getOwner() {
		return this.owner ;
	}

}
