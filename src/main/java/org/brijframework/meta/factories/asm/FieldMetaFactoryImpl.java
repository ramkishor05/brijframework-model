package org.brijframework.meta.factories.asm;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.brijframework.container.Container;
import org.brijframework.group.Group;
import org.brijframework.meta.KeyInfo;
import org.brijframework.meta.container.MetaContainer;
import org.brijframework.meta.factories.FieldMetaFactory;
import org.brijframework.meta.impl.PropertyMeta;
import org.brijframework.meta.reflect.FieldMeta;
import org.brijframework.support.model.Assignable;
import org.brijframework.support.model.DepandOn;

@DepandOn(depand = ClassMetaFactoryImpl.class)
public class FieldMetaFactoryImpl implements FieldMetaFactory<FieldMeta> {
	
	public static String PROPERTIES = "PROPERTIES";
	
	private ConcurrentHashMap<KeyInfo, FieldMeta> cache = new ConcurrentHashMap<>();
	
	private Container container = MetaContainer.getContainer();

	protected FieldMetaFactoryImpl() {
	}

	private static FieldMetaFactoryImpl factory;

	@Assignable
	public static FieldMetaFactoryImpl getFactory() {
		if (factory == null) {
			factory = new FieldMetaFactoryImpl();
			factory.loadFactory();
		}
		return factory;
	}

	@Override
	public Container getContainer() {
		return container;
	}

	@Override
	public void setContainer(Container container) {
		this.container = container;
	}

	@Override
	public FieldMetaFactoryImpl clear() {
		if (getCache() != null) {
			getCache().clear();
		}
		return this;
	}

	@SuppressWarnings("unchecked")
	public FieldMetaFactoryImpl loadFactory() {
		this.clear();
		if (getContainer() != null) {
			Group group = getContainer().get(PROPERTIES);
			if (group != null) {
				getCache().putAll(group.getCache());
			}
		}
		return this;
	}

	@SuppressWarnings("unchecked")
	public ConcurrentHashMap<KeyInfo, FieldMeta> getCache() {
		if(cache.isEmpty()) {
			Group group=getContainer().get(PROPERTIES);
			if(group!=null)
			cache.putAll(group.getCache());
		}
		return cache;
	}

	public FieldMeta getFieldInfo(String id) {
		for (FieldMeta metaInfo : getCache().values()) {
			if (metaInfo.getId().equals(id)) {
				return metaInfo;
			}
		}
		return null;
	}

	public FieldMeta getFieldInfo(String model, String name) {
		for (FieldMeta metaInfo : getCache().values()) {
			if (metaInfo.getOwner().getName().equals(model) && metaInfo.getName().equals(name)) {
				return metaInfo;
			}
		}
		return null;
	}

	public void loadContainer(String groupKey, PropertyMeta metaInfo) {
		if (getContainer() == null) {
			return;
		}
		Group group = getContainer().load(groupKey);
		group.add(metaInfo.getId(), metaInfo);
		getContainer().merge(groupKey, group);
	}

	@Override
	public Object groupKey() {
		return null;
	}

	@Override
	public FieldMeta getFieldMetaInfo(String perantId, String targetId) {
		return null;
	}

	@Override
	public List<FieldMeta> getFieldMetaInfo(String parentID) {
		return null;
	}

	@Override
	public FieldMeta FieldMetaInfo(String targetId) {
		return null;
	}

	@Override
	public void register(FieldMeta meta) {
		
	}

}
