package org.brijframework.model.info;

public interface RelationPropertyModelMetaData extends PropertyModelMetaData{

	public String getMappedBy();
	
	public Class<?> getTargetClass();

	public ClassModelMetaData getTargetClassMetaInfo() ;
}
