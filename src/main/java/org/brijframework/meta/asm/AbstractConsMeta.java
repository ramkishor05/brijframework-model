package org.brijframework.meta.asm;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

import org.brijframework.meta.reflect.ClassMeta;
import org.brijframework.meta.reflect.ConstMeta;
import org.brijframework.meta.reflect.ParamMeta;

public abstract class AbstractConsMeta extends AbstractMetaInfo<Constructor<?>> implements ConstMeta{

	private ClassMeta owner;
	private Constructor<?> target;
	private Set<ParamMeta> parametersInfo;
	private Type[] arguments;
	private Object[] values;
	
	@Override
	public ClassMeta getOwner() {
		return owner;
	}

	@Override
	public Constructor<?> getTarget() {
		return target;
	}

	@Override
	public Set<ParamMeta> getParametersInfo() {
		if(parametersInfo==null) {
			parametersInfo=new HashSet<>();
		}
		return parametersInfo;
	}

	@Override
	public void setParametersInfo(Set<ParamMeta> parametersInfo) {
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

	public void setOwner(ClassMeta owner) {
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
