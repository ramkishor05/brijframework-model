package org.brijframework.model.info;

public interface PptModelInfoGroup {
	
	public String getId();
	
	public String getName();
	
	public PptModelInfo getFieldMeta();

	public PptModelInfo getSetterMeta();
	
	public PptModelInfo getGetterMeta();

	public OwnerModelInfo getOwner();
	
}
