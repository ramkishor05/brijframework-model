package org.brijframework.model.diffination.asm;

import org.brijframework.model.diffination.PropertyModelMetaData;
import org.brijframework.model.diffination.PropertyModelMetaDataGroup;
import org.brijframework.model.diffination.TypeModelDiffination;

public abstract class AbstractPropertyModelMetaDataGroup extends AbstractModelMetaData<PropertyModelMetaData>  implements PropertyModelMetaDataGroup {
	private PropertyModelMetaData fieldMeta;
	private PropertyModelMetaData setterMeta;
	private PropertyModelMetaData getterMeta;
	private TypeModelDiffination owner;
	
	public AbstractPropertyModelMetaDataGroup(TypeModelDiffination owner) {
		this.owner=owner;
	}
	
	@Override
	public TypeModelDiffination getOwner() {
		return owner;
	}

	@Override
	public PropertyModelMetaData getFieldMeta() {
		return fieldMeta;
	}
	
	public void setFieldMeta(PropertyModelMetaData fieldMeta) {
		this.fieldMeta = fieldMeta;
	}

	@Override
	public PropertyModelMetaData getSetterMeta() {
		return setterMeta;
	}
	
	public void setSetterMeta(PropertyModelMetaData setterMeta) {
		this.setterMeta = setterMeta;
	}

	@Override
	public PropertyModelMetaData getGetterMeta() {
		return getterMeta;
	}
	
	public void setGetterMeta(PropertyModelMetaData getterMeta) {
		this.getterMeta = getterMeta;
	}

}
