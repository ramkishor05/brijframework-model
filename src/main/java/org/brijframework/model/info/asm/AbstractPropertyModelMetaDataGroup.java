package org.brijframework.model.info.asm;

import org.brijframework.model.info.ClassModelMetaData;
import org.brijframework.model.info.PropertyModelMetaDataGroup;
import org.brijframework.model.info.PropertyModelMetaData;

public abstract class AbstractPropertyModelMetaDataGroup extends AbstractModelMetaData<PropertyModelMetaData>  implements PropertyModelMetaDataGroup {
	private PropertyModelMetaData fieldMeta;
	private PropertyModelMetaData setterMeta;
	private PropertyModelMetaData getterMeta;
	private ClassModelMetaData owner;
	
	public AbstractPropertyModelMetaDataGroup(ClassModelMetaData owner) {
		this.owner=owner;
	}
	
	@Override
	public ClassModelMetaData getOwner() {
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
