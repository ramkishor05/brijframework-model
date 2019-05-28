package org.brijframework.meta.factories.asm;

import java.lang.reflect.AccessibleObject;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import org.brijframework.container.Container;
import org.brijframework.group.Group;
import org.brijframework.meta.KeyInfo;
import org.brijframework.meta.container.MetaContainer;
import org.brijframework.meta.factories.FieldMetaFactory;
import org.brijframework.meta.helper.PropertyMetaHelper;
import org.brijframework.meta.impl.PropertyMeta;
import org.brijframework.meta.impl.RelPtpMeta;
import org.brijframework.meta.reflect.ClassMeta;
import org.brijframework.meta.setup.FieldMetaSetup;
import org.brijframework.meta.setup.RelationMetaSetup;
import org.brijframework.support.model.Assignable;
import org.brijframework.support.model.DepandOn;
import org.brijframework.support.model.Property;
import org.brijframework.support.model.Relation;

@DepandOn(depand = MetaFactoryImpl.class)
public class PropertyMetaFactoryImpl implements FieldMetaFactory<PropertyMeta> {
	
	public static String PROPERTIES = "PROPERTIES";
	
	private ConcurrentHashMap<KeyInfo, PropertyMeta> cache = new ConcurrentHashMap<>();
	
	private Container container = MetaContainer.getContainer();

	protected PropertyMetaFactoryImpl() {
	}

	private static PropertyMetaFactoryImpl factory;

	@Assignable
	public static PropertyMetaFactoryImpl getFactory() {
		if (factory == null) {
			factory = new PropertyMetaFactoryImpl();
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
	public PropertyMetaFactoryImpl clear() {
		if (getCache() != null) {
			getCache().clear();
		}
		return this;
	}

	@SuppressWarnings("unchecked")
	public PropertyMetaFactoryImpl loadFactory() {
		this.clear();
		if (getContainer() != null) {
			Group group = getContainer().get(PROPERTIES);
			if (group != null) {
				getCache().putAll(group.getCache());
			}
		}
		return this;
	}

	@Override
	public ConcurrentHashMap<KeyInfo, PropertyMeta> getCache() {
		return cache;
	}

	public PropertyMeta getPropertyInfo(String id) {
		for (PropertyMeta metaInfo : getCache().values()) {
			if (metaInfo.getKeyInfo().getId().equals(id)) {
				return metaInfo;
			}
		}
		return null;
	}

	public PropertyMeta getPropertyInfo(String model, String name) {
		for (PropertyMeta metaInfo : getCache().values()) {
			if (metaInfo.getOwner().getName().equals(model) && metaInfo.getName().equals(name)) {
				return metaInfo;
			}
		}
		return null;
	}

	public void register(String model, AccessibleObject target, Property property) {
		ClassMeta owner = MetaFactoryImpl.getFactory().getMeta(model);
		Objects.requireNonNull(owner, "owner not found");
		PropertyMeta metaInfo = PropertyMetaHelper.getPropertyMetaInfo(owner, target, property);
		getCache().put(metaInfo.getKeyInfo(), metaInfo);
		owner.getProperties().put(metaInfo.getKeyInfo(), metaInfo);
		loadContainer(PROPERTIES, metaInfo);
	}
	
	public void register(String model, AccessibleObject target, Relation property) {
		ClassMeta owner = MetaFactoryImpl.getFactory().getMeta(model);
		Objects.requireNonNull(owner, "owner not found");
		org.brijframework.meta.impl.RelPtpMeta metaInfo = PropertyMetaHelper.getPropertyMetaInfo(owner, target, property);
		getCache().put(metaInfo.getKeyInfo(), metaInfo);
		owner.getRelations().put(metaInfo.getKeyInfo(), metaInfo);
		loadContainer(PROPERTIES, metaInfo);
	}

	public void register(String model, AccessibleObject target, FieldMetaSetup property) {
		ClassMeta owner = MetaFactoryImpl.getFactory().getMeta(model);
		Objects.requireNonNull(owner, "owner not found");
		PropertyMeta metaInfo = PropertyMetaHelper.getPropertyMetaInfo(owner, target, property);
		getCache().put(metaInfo.getKeyInfo(), metaInfo);
		owner.getProperties().put(metaInfo.getKeyInfo(), metaInfo);
		loadContainer(PROPERTIES, metaInfo);
	}
	
	public void register(String model, AccessibleObject target, RelationMetaSetup property) {
		ClassMeta owner = MetaFactoryImpl.getFactory().getMeta(model);
		Objects.requireNonNull(owner, "owner not found");
		RelPtpMeta  metaInfo = PropertyMetaHelper.getPropertyMetaInfo(owner, target, property);
		getCache().put(metaInfo.getKeyInfo(), metaInfo);
		owner.getRelations().put(metaInfo.getKeyInfo(), metaInfo);
		loadContainer(PROPERTIES, metaInfo);
	}

	public void loadContainer(String groupKey, PropertyMeta metaInfo) {
		if (getContainer() == null) {
			return;
		}
		Group group = getContainer().load(groupKey);
		group.add(metaInfo.getKeyInfo(), metaInfo);
		getContainer().merge(groupKey, group);
	}

	@Override
	public Object groupKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyMeta> getFieldMetaInfo(String parentID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PropertyMeta FieldMetaInfo(String targetId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PropertyMeta getFieldMetaInfo(String perantId, String targetId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void register(PropertyMeta meta) {
		// TODO Auto-generated method stub
		
	}

}
