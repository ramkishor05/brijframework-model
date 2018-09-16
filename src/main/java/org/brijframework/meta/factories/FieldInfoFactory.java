package org.brijframework.meta.factories;

import java.util.List;

import org.brijframework.meta.properties.FieldInfo;

public interface FieldInfoFactory extends MetaInfoFactory{

	List<? extends FieldInfo> getPropertiesInfo(String peranrId, String targetId);

	List<? extends FieldInfo> getPropertiesInfo(String parentID);
}
