package org.brijframework.model.metadata;

public interface RelationPropertyModelMetaData extends PropertyModelMetaData{

	public String getMappedBy();
	
	public Class<?> getTargetClass();

	public TypeModelMetaData getTargetClassMetaInfo() ;
}
