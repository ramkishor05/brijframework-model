package org.brijframework.model.mapper.model;

import java.util.HashMap;
import java.util.Map;

import org.brijframework.model.resource.asm.AbstractModelResource;

public class TypeModelMapperResource  extends AbstractModelResource<Class<?>>  {

	private String source;

	private String destination;
	
	private Class<?> type;
	
	private Map<String,PropertyModelMapperResource> properties;
	
	public Map<String, PropertyModelMapperResource> getProperties() {
		if(properties==null) {
			properties=new HashMap<String, PropertyModelMapperResource>();
		}
		return properties;
	}
	
	public void setProperties(Map<String, PropertyModelMapperResource> properties) {
		this.properties = properties;
	}
	
	@Override
	public Class<?> getType() {
		return type;
	}

	public void setType(Class<?> type) {
		this.type = type;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}
	
}
