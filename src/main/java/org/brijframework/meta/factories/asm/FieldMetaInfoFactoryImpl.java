package org.brijframework.meta.factories.asm;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.brijframework.container.Container;
import org.brijframework.group.Group;
import org.brijframework.meta.KeyInfo;
import org.brijframework.meta.asm.annotation.PropertyMetaInfo;
import org.brijframework.meta.container.MetaContainer;
import org.brijframework.meta.factories.FieldMetaInfoFactory;
import org.brijframework.meta.reflect.FieldMetaInfo;
import org.brijframework.support.model.Assignable;
import org.brijframework.support.model.DepandOn;

@DepandOn(depand = ClassMetaInfoFactoryImpl.class)
public class FieldMetaInfoFactoryImpl implements FieldMetaInfoFactory<FieldMetaInfo> {
	
	public static String PROPERTIES = "PROPERTIES";
	
	private ConcurrentHashMap<KeyInfo, FieldMetaInfo> cache = new ConcurrentHashMap<>();
	
	private Container container = MetaContainer.getContainer();

	protected FieldMetaInfoFactoryImpl() {
	}

	private static FieldMetaInfoFactoryImpl factory;

	@Assignable
	public static FieldMetaInfoFactoryImpl getFactory() {
		if (factory == null) {
			factory = new FieldMetaInfoFactoryImpl();
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
	public FieldMetaInfoFactoryImpl clear() {
		if (getCache() != null) {
			getCache().clear();
		}
		return this;
	}

	@SuppressWarnings("unchecked")
	public FieldMetaInfoFactoryImpl loadFactory() {
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
	public ConcurrentHashMap<KeyInfo, FieldMetaInfo> getCache() {
		if(cache.isEmpty()) {
			Group group=getContainer().get(PROPERTIES);
			if(group!=null)
			cache.putAll(group.getCache());
		}
		return cache;
	}

	public FieldMetaInfo getFieldInfo(String id) {
		for (FieldMetaInfo metaInfo : getCache().values()) {
			if (metaInfo.getId().equals(id)) {
				return metaInfo;
			}
		}
		return null;
	}

	public FieldMetaInfo getFieldInfo(String model, String name) {
		for (FieldMetaInfo metaInfo : getCache().values()) {
			if (metaInfo.getOwner().getName().equals(model) && metaInfo.getName().equals(name)) {
				return metaInfo;
			}
		}
		return null;
	}

	public void loadContainer(String groupKey, PropertyMetaInfo metaInfo) {
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
	public FieldMetaInfo getFieldMetaInfo(String perantId, String targetId) {
		return null;
	}

	@Override
	public List<FieldMetaInfo> getFieldMetaInfo(String parentID) {
		return null;
	}

	@Override
	public FieldMetaInfo FieldMetaInfo(String targetId) {
		return null;
	}

	@Override
	public void register(FieldMetaInfo meta) {
		
	}

}
