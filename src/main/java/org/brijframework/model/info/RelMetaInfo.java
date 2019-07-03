package org.brijframework.model.info;

public interface RelMetaInfo extends PptModelInfo{

	public String getMappedBy();
	
	public Class<?> getTargetClass();

	public OwnerModelInfo getTargetClassMetaInfo() ;
}
