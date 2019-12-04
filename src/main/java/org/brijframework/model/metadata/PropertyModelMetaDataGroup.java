package org.brijframework.model.metadata;

import org.brijframework.model.ModelMetaData;

public interface PropertyModelMetaDataGroup extends ModelMetaData<PropertyModelMetaData> {
	
	public String getId();
	
	public String getName();
	
	public PropertyModelMetaData getFieldMeta();

	public PropertyModelMetaData getSetterMeta();
	
	public PropertyModelMetaData getGetterMeta();

	public TypeModelMetaData getOwner();
	
}
