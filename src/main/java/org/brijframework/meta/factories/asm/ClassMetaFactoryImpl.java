package org.brijframework.meta.factories.asm;

import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.brijframework.container.Container;
import org.brijframework.group.Group;
import org.brijframework.meta.KeyInfo;
import org.brijframework.meta.factories.ClassMetaFactory;
import org.brijframework.meta.reflect.ClassMeta;
import org.brijframework.support.model.Assignable;

public class ClassMetaFactoryImpl extends MetaFactoryImpl implements ClassMetaFactory {
	
	private ConcurrentHashMap<KeyInfo, ClassMeta> cache = new ConcurrentHashMap<>();
	
	private Container container;
	
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

	@SuppressWarnings("unchecked")
	public ClassMetaFactoryImpl loadFactory() {
		this.clear();
		if (getContainer() != null) {
			for(Entry<Object, Group> entry: getContainer().getCache().entrySet()) {
				Group group = entry.getValue();
				if(group!=null) {
					getCache().putAll(group.getCache());
				}
			}
		}
		return this;
	}

	public ConcurrentHashMap<KeyInfo, ClassMeta> getCache() {
		return cache;
	}

	@Override
	public Container getContainer() {
		return container;
	}

	@Override
	public void setContainer(Container container) {
		this.container=container;
	}

	@Override
	public ClassMetaFactoryImpl clear() {
		if (getCache() != null) {
			getCache().clear();
		}
		return this;
	}

	public ClassMeta getClassInfo(String id) {
		for(Entry<KeyInfo, ClassMeta> entry:getCache().entrySet()) {
			if(entry.getKey().getId().equals(id)) {
				return entry.getValue();
			}
		}
		return getContainer(id);
	}

	public void register(Class<?> target, ClassMeta metaInfo) {
		this.getCache().put(metaInfo.getKeyInfo(), metaInfo);
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
	public Object groupKey() {
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

}