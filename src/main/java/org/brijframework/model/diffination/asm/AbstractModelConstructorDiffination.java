package org.brijframework.model.diffination.asm;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.brijframework.model.diffination.ModelConstructorDiffination;
import org.brijframework.model.diffination.ModelParameterDiffination;
import org.brijframework.model.diffination.ModelTypeDeffination;

public abstract class AbstractModelConstructorDiffination extends AbstractModelDiffination<Constructor<?>> implements ModelConstructorDiffination{

	private ModelTypeDeffination owner;
	private Constructor<?> target;
	private Set<ModelParameterDiffination<?>> parameters;
	private Type[] arguments;
	
	@Override
	public ModelTypeDeffination getOwner() {
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

	public void setOwner(ModelTypeDeffination owner) {
		this.owner = owner;
	}

	public void setTarget(Constructor<?> target) {
		this.target = target;
	}

	@Override
	public String toString() {
		return super.toString()+" [owner=" + owner.getId() + ", target=" + target + ", parameters="
				+ parameters + ", arguments=" + Arrays.toString(arguments) + "]";
	}

	
}
