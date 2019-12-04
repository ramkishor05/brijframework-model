package org.brijframework.model.metadata.asm;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

import org.brijframework.model.metadata.ConstructorModelMetaData;
import org.brijframework.model.metadata.ParameterModelMetaData;
import org.brijframework.model.metadata.TypeModelMetaData;

public abstract class AbstractConstructorModelMetaData extends AbstractModelMetaData<Constructor<?>> implements ConstructorModelMetaData{

	private TypeModelMetaData owner;
	private Constructor<?> target;
	private Set<ParameterModelMetaData<?>> parameters;
	private Type[] arguments;
	private Object[] values;
	
	@Override
	public TypeModelMetaData getOwner() {
		return owner;
	}

	@Override
	public Constructor<?> getType() {
		return target;
	}

	@Override
	public Set<ParameterModelMetaData<?>> getParameters() {
		if(parameters==null) {
			parameters=new HashSet<>();
		}
		return parameters;
	}

	@Override
	public void setParameters(Set<ParameterModelMetaData<?>> parametersInfo) {
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

	public void setOwner(TypeModelMetaData owner) {
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
