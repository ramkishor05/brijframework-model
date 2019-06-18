package org.brijframework.meta.reflect;

public interface FieldGroup {
	
	public String getId();
	
	public String getName();
	
	public FieldMeta getFieldMeta();

	public FieldMeta getSetterMeta();
	
	public FieldMeta getGetterMeta();

	public ClassMeta getOwner();
	
}
