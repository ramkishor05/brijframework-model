package org.brijframework.meta.factories.asm;

import java.util.List;
import java.util.Map.Entry;

import org.brijframework.group.Group;
import org.brijframework.meta.factories.ClassMetaFactory;
import org.brijframework.meta.reflect.ClassMeta;
import org.brijframework.meta.reflect.FieldMeta;
import org.brijframework.support.model.Assignable;

public class ClassMetaFactoryImpl extends MetaFactoryImpl implements ClassMetaFactory {
	
	
	protected ClassMetaFactoryImpl() {
	}

	protected static ClassMetaFactoryImpl factory;

	@Assignable
	public static ClassMetaFactoryImpl getFactory() {
		if (factory == null) {
			factory = new ClassMetaFactoryImpl();
		}
		return factory;
	}

	@Override
	public ClassMetaFactoryImpl clear() {
		if (getCache() != null) {
			getCache().clear();
		}
		return this;
	}

	public ClassMeta getClassInfo(String id) {
		for(Entry<String, ClassMeta> entry:getCache().entrySet()) {
			if(entry.getKey().equals(id)) {
				return entry.getValue();
			}
		}
		return getContainer(id);
	}

	public void register(Class<?> target, ClassMeta metaInfo) {
		this.getCache().put(metaInfo.getId(), metaInfo);
		loadContainer(metaInfo);
	}
	
	public void loadContainer(String groupKey, ClassMeta metaInfo) {
		if (getContainer() == null) {
			return;
		}
		Group group = getContainer().load(groupKey);
		if(!group.containsKey(metaInfo.getId())) {
			group.add(metaInfo.getId(), metaInfo);
		}else {
			group.update(metaInfo.getId(), metaInfo);
		}
		getContainer().merge(groupKey, group);
	}

	public ClassMeta getContainer(String groupKey, String modelKey) {
		if (getContainer() == null) {
			return null;
		}
		Group group = getContainer().load(groupKey);
		if(group!=null) {
			return group.get(modelKey);
		}
		return null;
	}

	@Override
	public List<ClassMeta> getClassInfoList(Class<?> cls) {
		return null;
	}

	@Override
	public List<ClassMeta> getClassInfoList(Class<?> target, String parentID) {
		return null;
	}

	public FieldMeta getFieldMeta(String simpleName, String _keyPath) {
		ClassMeta classMeta=getClassInfo(simpleName);
		if(classMeta==null) {
			return null;
		}
		return classMeta.getPropertyInfo(_keyPath);
	}

}