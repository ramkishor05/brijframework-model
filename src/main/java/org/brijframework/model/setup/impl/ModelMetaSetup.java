package org.brijframework.model.setup.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.brijframework.model.setup.ClassMetaSetup;
import org.brijframework.model.setup.ConstMetaSetup;
import org.brijframework.model.setup.FieldMetaSetup;

public class ModelMetaSetup implements ClassMetaSetup {
	private String id;
	private String name;
	private String target;
	private String access;
	private String scope;
	private String type;
	private String extend;
	private Map<String, FieldMetaSetup> properties;
	private ConstMetaSetup constructor;

	@Override
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	@Override
	public String getAccess() {
		return access;
	}

	public void setAccess(String access) {
		this.access = access;
	}

	@Override
	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}
	
	public void setExtend(String extend) {
		this.extend = extend;
	}
	
	@Override
	public String getExtend() {
		return extend;
	}

	@Override
	public Map<String, FieldMetaSetup> getProperties() {
		if(properties==null) {
			properties=new HashMap<>();
		}
		return properties;
	}

	public void setProperties(Map<String, FieldMetaSetup> properties) {
		this.properties = properties;
	}

	public void addProperty(FieldMetaSetup setup) {
		Objects.requireNonNull(setup.getId(), "Property id should not empty.");
		if (this.getProperties() == null) {
			this.setProperties(new HashMap<>());
		}
		this.getProperties().put(setup.getId(), setup);
	}

	public FieldMetaSetup getProperty(String setup) {
		if (this.getProperties() == null) {
			return null;
		}
		return this.getProperties().get(setup);
	}

	@Override
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public ConstMetaSetup getConstructor() {
		return constructor;
	}
	
	public void setConstructor(ConstMetaSetup constructor) {
		this.constructor = constructor;
	}
}
