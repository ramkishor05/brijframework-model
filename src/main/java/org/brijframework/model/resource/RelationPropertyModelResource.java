package org.brijframework.model.resource;

public interface RelationPropertyModelResource<E> extends PropertyModelResource<E>{
	
	public String getRefer();

	String getMappedBy();

	String getWired();

	String getMappedTo();
	
}
