package org.brijframework.meta.reflect;

public interface ReferMeta extends FieldMeta{

	public String getMappedBy();
	
	public Class<?> getTargetClass();

	public ClassMeta getRelationClassMeta() ;
}
