package org.brijframework.model.info.asm;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

import org.brijframework.model.info.OwnerModelInfo;
import org.brijframework.model.info.ConstModelInfo;
import org.brijframework.model.setup.ParamMetaSetup;

public abstract class AbstractConstModelInfo extends AbstractModelInfo<Constructor<?>> implements ConstModelInfo{

	private OwnerModelInfo owner;
	private Constructor<?> target;
	private Set<ParamMetaSetup> parametersInfo;
	private Type[] arguments;
	private Object[] values;
	
	@Override
	public OwnerModelInfo getOwner() {
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

	public void setOwner(OwnerModelInfo owner) {
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
