package org.brijframework.model.setup.impl;

import java.util.HashMap;
import java.util.Objects;

import org.brijframework.model.resource.PropertyModelResource;
import org.brijframework.model.resource.asm.AbstractClassModelResource;

public class ClassModelResourceObject extends AbstractClassModelResource{

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
}
