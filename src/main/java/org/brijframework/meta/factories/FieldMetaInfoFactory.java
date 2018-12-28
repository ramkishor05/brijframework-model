package org.brijframework.meta.factories;

import java.lang.reflect.AccessibleObject;
import java.util.List;
import java.util.Map;

import org.brijframework.meta.reflect.FieldMetaInfo;
import org.brijframework.support.enums.Access;
import org.brijframework.util.reflect.FieldUtil;
import org.brijframework.util.reflect.PackUtil;

public interface FieldMetaInfoFactory<T extends FieldMetaInfo> extends MetaInfoFactory<FieldMetaInfo>{

	List<T> getPropertiesInfo(String peranrId, String targetId);

	List<T> getPropertiesInfo(String parentID);
	
	T getPropertyInfo(String parentID);
	
	@Override
	default FieldMetaInfoFactory<T> loadFactory() {
		this.clear();
		PackUtil.getProjectClasses().forEach(cls -> {
			FieldUtil.getAllField(cls,Access.PRIVATE).forEach(field->{
				this.register(cls,field);
			});
		});
		this.loadToContainer();
		return this;
	}

	void register(Class<?> cls, AccessibleObject field);
	void register(Class<?> cls, Map<String, Object> properties);
	void register(Class<?> cls, AccessibleObject field, Map<String, Object> properties);

	
}
