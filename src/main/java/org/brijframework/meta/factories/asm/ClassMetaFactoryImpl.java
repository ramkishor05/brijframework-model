package org.brijframework.meta.factories.asm;

import java.util.List;
import java.util.Map.Entry;

import org.brijframework.group.Group;
import org.brijframework.meta.factories.ClassMetaInfoFactory;
import org.brijframework.meta.info.ClassMetaInfo;
import org.brijframework.meta.info.FieldMetaInfo;
import org.brijframework.support.model.Assignable;

public class ClassMetaFactoryImpl extends MetaSetupFactoryImpl implements ClassMetaInfoFactory {
	
	
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

	public ClassMetaInfo getClassInfo(String id) {
		for(Entry<String, ClassMetaInfo> entry:getCache().entrySet()) {
			if(entry.getKey().equals(id)) {
				return entry.getValue();
			}
		}
		return getContainer(id);
	}

	public void register(Class<?> target, ClassMetaInfo metaInfo) {
		this.getCache().put(metaInfo.getId(), metaInfo);
		loadContainer(metaInfo);
	}
	
	public void loadContainer(String groupKey, ClassMetaInfo metaInfo) {
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

	public ClassMetaInfo getContainer(String groupKey, String modelKey) {
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
	public List<ClassMetaInfo> getClassInfoList(Class<?> cls) {
		return null;
	}

	@Override
	public List<ClassMetaInfo> getClassInfoList(Class<?> target, String parentID) {
		return null;
	}

	public FieldMetaInfo getFieldMeta(String simpleName, String _keyPath) {
		ClassMetaInfo classMeta=getClassInfo(simpleName);
		if(classMeta==null) {
			return null;
		}
		return classMeta.getPropertyInfo(_keyPath);
	}

}