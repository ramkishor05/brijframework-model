package org.brijframework.meta.asm;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.util.Set;

import org.brijframework.meta.KeyInfo;
import org.brijframework.meta.reflect.ClassMetaInfo;
import org.brijframework.meta.reflect.ConsMetaInfo;
import org.brijframework.meta.reflect.ParamMetaInfo;

public abstract class AbstractConsMetaInfo extends AbstractMetaInfo implements ConsMetaInfo{

	private ClassMetaInfo owner;
	private Constructor<?> target;
	private KeyInfo keyInfo ;
	private Set<ParamMetaInfo> parametersInfo;
	private Type[] arguments;
	
	@Override
	public ClassMetaInfo getOwner() {
		return owner;
	}

	@Override
	public KeyInfo getKeyInfo() {
		return keyInfo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Constructor<?> getTarget() {
		return target;
	}

	@Override
	public Set<ParamMetaInfo> getParametersInfo() {
		return parametersInfo;
	}

	@Override
	public void setParametersInfo(Set<ParamMetaInfo> parametersInfo) {
		this.parametersInfo=parametersInfo;
	}

	@Override
	public void setArguments(Type[] arguments) {
		this.arguments=arguments;
	}

	@Override
	public Type[] getArguments() {
		return arguments;
	}

}
