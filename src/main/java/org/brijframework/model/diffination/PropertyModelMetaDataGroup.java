package org.brijframework.model.diffination;

import org.brijframework.model.ModelDiffination;

public interface PropertyModelMetaDataGroup extends ModelDiffination<PropertyModelMetaData> {
	
	public String getId();
	
	public String getName();
	
	public PropertyModelMetaData getFieldMeta();

	public PropertyModelMetaData getSetterMeta();
	
	public PropertyModelMetaData getGetterMeta();

	public TypeModelDiffination getOwner();
	
}
