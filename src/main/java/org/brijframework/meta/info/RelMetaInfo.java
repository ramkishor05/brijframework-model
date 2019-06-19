package org.brijframework.meta.info;

public interface RelMetaInfo extends FieldMetaInfo{

	public String getMappedBy();
	
	public Class<?> getTargetClass();

	public ClassMetaInfo getTargetClassMetaInfo() ;
}
