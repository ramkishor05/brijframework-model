package org.brijframework.model.info.asm;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

import org.brijframework.model.info.ClassModelMetaData;
import org.brijframework.model.info.ConstructorModelMetaData;
import org.brijframework.model.info.ParameterModelMetaData;

public abstract class AbstractConstructorModelMetaData extends AbstractModelMetaData<Constructor<?>> implements ConstructorModelMetaData{

	private ClassModelMetaData owner;
	private Constructor<?> target;
	private Set<ParameterModelMetaData<?>> parameters;
	private Type[] arguments;
	private Object[] values;
	
	@Override
	public ClassModelMetaData getOwner() {
		return owner;
	}

	@Override
	public Constructor<?> getTarget() {
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

	public void setOwner(ClassModelMetaData owner) {
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
