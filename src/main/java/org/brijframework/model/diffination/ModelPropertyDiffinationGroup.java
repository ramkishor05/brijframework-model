package org.brijframework.model.diffination;

import org.brijframework.model.ModelDiffination;

public interface ModelPropertyDiffinationGroup extends ModelDiffination<ModelPropertyDiffination> {
	
	public String getId();
	
	public String getName();
	
	public ModelPropertyDiffination getFieldMeta();

	public ModelPropertyDiffination getSetterMeta();
	
	public ModelPropertyDiffination getGetterMeta();

	public ModelTypeDeffination getOwner();
	
}
