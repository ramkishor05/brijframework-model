package org.brijframework.meta.asm;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

import org.brijframework.meta.info.ClassMetaInfo;
import org.brijframework.meta.info.ConstMetaInfo;
import org.brijframework.meta.setup.ParamMetaSetup;

public abstract class AbstractConstMetaInfo extends AbstractMetaInfo<Constructor<?>> implements ConstMetaInfo{

	private ClassMetaInfo owner;
	private Constructor<?> target;
	private Set<ParamMetaSetup> parametersInfo;
	private Type[] arguments;
	private Object[] values;
	
	@Override
	public ClassMetaInfo getOwner() {
		return owner;
	}

	@Override
	public Constructor<?> getTarget() {
		return target;
	}

	@Override
	public Set<ParamMetaSetup> getParams() {
		if(parametersInfo==null) {
			parametersInfo=new HashSet<>();
		}
		return parametersInfo;
	}

	@Override
	public void setParametersInfo(Set<ParamMetaSetup> parametersInfo) {
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

	public void setOwner(ClassMetaInfo owner) {
		this.owner = owner;
	}

	public void setTarget(Constructor<?> target) {
		this.target = target;
	}

	public void setValues(Object[] values) {
		this.values = values;
	}
	
	@Override
	public Object[] getValues() {
		return this.values;
	}
}
