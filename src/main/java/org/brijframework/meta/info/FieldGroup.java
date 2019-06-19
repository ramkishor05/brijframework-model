package org.brijframework.meta.info;

public interface FieldGroup {
	
	public String getId();
	
	public String getName();
	
	public FieldMetaInfo getFieldMeta();

	public FieldMetaInfo getSetterMeta();
	
	public FieldMetaInfo getGetterMeta();

	public ClassMetaInfo getOwner();
	
}
