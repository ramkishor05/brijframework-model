package org.brijframework.model.resource.asm;

import java.util.HashMap;
import java.util.Map;

import org.brijframework.model.resource.ClassModelResource;
import org.brijframework.model.resource.ConstructorModelResource;
import org.brijframework.model.resource.PropertyModelResource;

public class AbstractClassModelResource extends AbstractModelResource<String> implements ClassModelResource{

	private String type;
	private String extend;

	private Map<String, PropertyModelResource<?>> properties;
	
	private Map<String, PropertyModelResource<?>> relations;
	
	private ConstructorModelResource constructor;
	
	private String scope;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public void setExtend(String extend) {
		this.extend = extend;
	}
	
	public String getExtend() {
		return extend;
	}
	
	public ConstructorModelResource getConstructor() {
		return constructor;
	}
	
	public void setConstructor(ConstructorModelResource constructor) {
		this.constructor = constructor;
	}

	public Map<String, PropertyModelResource<?>> getProperties() {
		if(properties==null) {
			properties=new HashMap<>();
		}
		return properties;
	}

	public void setProperties(Map<String, PropertyModelResource<?>> properties) {
		this.properties = properties;
	}
	
	public Map<String, PropertyModelResource<?>> getRelations() {
		if(relations==null) {
			relations=new HashMap<>();
		}
		return relations;
	}
	
	public void setRelations(Map<String, PropertyModelResource<?>> relations) {
		this.relations = relations;
	}

	@Override
	public String getScope() {
		return this.scope;
	}
	
	public void setScope(String scope) {
		this.scope = scope;
	}

}
