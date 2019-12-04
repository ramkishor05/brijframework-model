package org.brijframework.model.metadata.asm;

import org.brijframework.model.metadata.PropertyModelMetaData;
import org.brijframework.model.metadata.PropertyModelMetaDataGroup;
import org.brijframework.model.metadata.TypeModelMetaData;

public abstract class AbstractPropertyModelMetaDataGroup extends AbstractModelMetaData<PropertyModelMetaData>  implements PropertyModelMetaDataGroup {
	private PropertyModelMetaData fieldMeta;
	private PropertyModelMetaData setterMeta;
	private PropertyModelMetaData getterMeta;
	private TypeModelMetaData owner;
	
	public AbstractPropertyModelMetaDataGroup(TypeModelMetaData owner) {
		this.owner=owner;
	}
	
	@Override
	public TypeModelMetaData getOwner() {
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
