package org.brijframework.model.resource.impl;

import java.util.HashMap;
import java.util.Objects;

import org.brijframework.model.resource.PropertyModelResource;
import org.brijframework.model.resource.asm.AbstractTypeModelResource;

public class TypeModelResourceImpl extends AbstractTypeModelResource{

	public void addProperty(PropertyModelResource<String> setup) {
		Objects.requireNonNull(setup.getId(), "Property id should not empty.");
		if (this.getProperties() == null) {
			this.setProperties(new HashMap<>());
		}
		this.getProperties().put(setup.getId(), setup);
	}

	public PropertyModelResource<?> getProperty(String setup) {
		if (this.getProperties() == null) {
			return null;
		}
		return this.getProperties().get(setup);
	}

	@Override
	public String toString() {
		return super.toString()+
				" ["
				+" id=" + getId() + 
				"  type=" + getType() + 
				", extend=" + getExtend() + 
				", access=" + getAccess()+ 
				", name=" + getName() + 
				", access="+ getAccess() +
				", constructor="+ getConstructor() +
				", getProperties=" + getProperties() +
				"]";
	}
	
	
}
