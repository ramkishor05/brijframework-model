package org.brijframework.model.diffination;

public interface ModelRelationDiffination extends ModelPropertyDiffination{

	public String getMappedBy();
	
	public Class<?> getTargetClass();

	public ModelTypeDeffination getTargetClassMetaInfo() ;
}
