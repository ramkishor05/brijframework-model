package org.brijframework.meta.asm.annotation;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;

import org.brijframework.meta.asm.AbstractClassMetaInfo;
import org.brijframework.meta.reflect.ConsMetaInfo;
import org.brijframework.meta.reflect.FieldMetaInfo;
import org.brijframework.meta.reflect.RelationMetaInfo;

public class ModelMetaInfo extends AbstractClassMetaInfo{
	
	public ModelMetaInfo(Class<?> target) {
		this.setTarget(target);
	}
	
	public ModelMetaInfo(Class<?> target,String id, String name) {
		this(target);
		this.setId(id);
		this.setName(name);
	}

	@Override
	public void papulate(Map<String, Object> map) {
	}

	@Override
	public FieldMetaInfo getPropertyInfo(String _key) {
		return null;
	}

	@Override
	public ConsMetaInfo getConstructor() {
		return null;
	}

	@Override
	public ConsMetaInfo getConstructorInfo(Type... types) {
		return null;
	}

	@Override
	public Collection<? extends ConsMetaInfo> getConstructors() {
		return null;
	}

	@Override
	public Collection<? extends FieldMetaInfo> getPropertiesInfo() {
		return null;
	}

	@Override
	public Collection<? extends RelationMetaInfo> getRelationInfoList() {
		return null;
	}
}
