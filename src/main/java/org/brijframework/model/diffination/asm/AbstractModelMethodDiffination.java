package org.brijframework.model.diffination.asm;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;

import org.brijframework.model.diffination.ModelMethodDiffination;
import org.brijframework.model.diffination.ModelParameterDiffination;
import org.brijframework.model.diffination.ModelTypeDeffination;

public abstract class AbstractModelMethodDiffination extends AbstractModelDiffination<Method> implements ModelMethodDiffination {

	private ModelTypeDeffination owner;
	private Method target;
	private Object value;
	private Class<?> type;
	private Type[] arguments;
	private Set<ModelParameterDiffination<?>> parameters;
	
	public ModelTypeDeffination getOwner() {
		return owner;
	}
	
	public void setTarget(Method target) {
		this.target = target;
	}
	
	@Override
	public Method getType() {
		return target;
	}
	
	public Object getValue() {
		return value;
	}
	
	public void setValue(Object value) {
		this.value = value;
	}

	public Method getTarget() {
		return target;
	}

	public void setOwner(ModelTypeDeffination owner) {
		this.owner = owner;
	}

	@Override
	public void setReturnType(Class<?> type) {
		this.type=type;
	}

	@Override
	public Class<?> getReturnType() {
		return this.type;
	}

	@Override
	public void setArguments(Type[] arguments) {
		this.arguments=arguments;
	}

	@Override
	public Type[] getArguments() {
		return this.arguments;
	}

	@Override
	public Set<ModelParameterDiffination<?>> getParameters() {
		return this.parameters;
	}

	@Override
	public void papulate(Map<String, Object> annotationData) {
	}
	
	
	public void setParameters(Set<ModelParameterDiffination<?>> parameters) {
		this.parameters = parameters;
	}
}
