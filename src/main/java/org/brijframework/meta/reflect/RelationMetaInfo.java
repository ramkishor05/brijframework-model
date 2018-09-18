package org.brijframework.meta.reflect;

public interface RelationMetaInfo extends FieldMetaInfo{

	public String getMappedBy();
	
	public Class<?> getTargetClass();

	public ClassMetaInfo getRelationClassMetaInfo() ;
}
