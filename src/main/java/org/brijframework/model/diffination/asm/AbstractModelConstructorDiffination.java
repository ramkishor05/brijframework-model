package org.brijframework.model.diffination.asm;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

import org.brijframework.model.diffination.ModelConstructorDiffination;
import org.brijframework.model.diffination.ModelParameterDiffination;
import org.brijframework.model.diffination.ModelTypeDiffination;

public abstract class AbstractModelConstructorDiffination extends AbstractModelDiffination<Constructor<?>> implements ModelConstructorDiffination{

	private ModelTypeDiffination owner;
	private Constructor<?> target;
	private Set<ModelParameterDiffination<?>> parameters;
	private Type[] arguments;
	private Object[] values;
	
	@Override
	public ModelTypeDiffination getOwner() {
		return owner;
	}

	@Override
	public Constructor<?> getType() {
		return target;
	}

	@Override
	public Set<ModelParameterDiffination<?>> getParameters() {
		if(parameters==null) {
			parameters=new HashSet<>();
		}
		return parameters;
	}

	@Override
	public void setParameters(Set<ModelParameterDiffination<?>> parametersInfo) {
		this.parameters=parametersInfo;
	}

	@Override
	public void setArguments(Type[] arguments) {
		this.arguments=arguments;
	}

	@Override
	public Type[] getArguments() {
		return arguments;
	}

	public void setOwner(ModelTypeDiffination owner) {
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
