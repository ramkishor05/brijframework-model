package org.brijframework.meta.asm;

import org.brijframework.meta.reflect.ClassMetaInfo;
import org.brijframework.meta.reflect.RelationMetaInfo;

@SuppressWarnings("unchecked")
public abstract class AbstractRelationMetaInfo extends AbstractFieldMetaInfo implements RelationMetaInfo{

	private String mappedBy;
	
	private Class<?> targetClass;
	
	private ClassMetaInfo  relationClassMetaInfo;
	
	
	public void setMappedBy(String mappedBy) {
		this.mappedBy = mappedBy;
	}
	
	@Override
	public String getMappedBy() {
		return mappedBy;
	}

	public void setTargetClass(Class<?> targetClass) {
		this.targetClass = targetClass;
	}

	@Override
	public Class<?> getTargetClass() {
		return targetClass;
	}

	public void setRelationClassMetaInfo(ClassMetaInfo relationClassMetaInfo) {
		this.relationClassMetaInfo = relationClassMetaInfo;
	}
	
	@Override
	public ClassMetaInfo getRelationClassMetaInfo() {
		return relationClassMetaInfo;
	}

}
