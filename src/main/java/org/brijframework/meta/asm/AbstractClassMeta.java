package org.brijframework.meta.asm;

import java.util.HashMap;
import java.util.Map;

import org.brijframework.meta.KeyInfo;
import org.brijframework.meta.reflect.ClassMeta;
import org.brijframework.meta.reflect.FieldMeta;
import org.brijframework.meta.reflect.ReferMeta;

public abstract class AbstractClassMeta extends AbstractMetaInfo<Class<?>> implements ClassMeta{
	
	private Map<KeyInfo,FieldMeta>  properties;
	private Map<KeyInfo,ReferMeta> relations;
	
	private Class<?> target;
	private String type;
	
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String getType() {
		return type;
	}
	
	public void setTarget(Class<?> target) {
		this.target = target;
	}

	@Override
	public Class<?> getTarget() {
		return target;
	}

	@Override
	public Map<KeyInfo, FieldMeta> getProperties() {
		if(properties==null) {
			properties=new HashMap<KeyInfo, FieldMeta>();
		}
		return properties;
	}

	@Override
	public Map<KeyInfo,ReferMeta> getRelations() {
		if(relations==null) {
			relations=new HashMap<KeyInfo, ReferMeta>();
		}
		return relations;
	}
}
