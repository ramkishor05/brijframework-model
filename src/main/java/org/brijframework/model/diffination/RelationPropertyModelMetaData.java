package org.brijframework.model.diffination;

public interface RelationPropertyModelMetaData extends PropertyModelMetaData{

	public String getMappedBy();
	
	public Class<?> getTargetClass();

	public TypeModelDiffination getTargetClassMetaInfo() ;
}
