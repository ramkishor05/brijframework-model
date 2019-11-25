package org.brijframework.model.info;

import org.brijframework.model.ModelMetaData;

public interface PropertyModelMetaDataGroup extends ModelMetaData<PropertyModelMetaData> {
	
	public String getId();
	
	public String getName();
	
	public PropertyModelMetaData getFieldMeta();

	public PropertyModelMetaData getSetterMeta();
	
	public PropertyModelMetaData getGetterMeta();

	public ClassModelMetaData getOwner();
	
}
