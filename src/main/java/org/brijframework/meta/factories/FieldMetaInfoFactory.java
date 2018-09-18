package org.brijframework.meta.factories;

import java.util.List;

import org.brijframework.meta.reflect.FieldMetaInfo;

public interface FieldMetaInfoFactory extends MetaInfoFactory{

	List<? extends FieldMetaInfo> getPropertiesInfo(String peranrId, String targetId);

	List<? extends FieldMetaInfo> getPropertiesInfo(String parentID);
}
