package org.brijframework.meta.factories.asm;

import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.brijframework.container.Container;
import org.brijframework.group.Group;
import org.brijframework.meta.KeyInfo;
import org.brijframework.meta.container.MetaContainer;
import org.brijframework.meta.factories.MetaFactory;
import org.brijframework.meta.helper.ModelMetaHelper;
import org.brijframework.meta.impl.ModelMeta;
import org.brijframework.meta.reflect.ClassMeta;
import org.brijframework.meta.setup.ClassMetaSetup;
import org.brijframework.support.model.Assignable;
import org.brijframework.support.model.Model;

public class MetaFactoryImpl implements MetaFactory<ClassMeta> {
	
	private ConcurrentHashMap<KeyInfo, ClassMeta> cache = new ConcurrentHashMap<>();
	private Container container = MetaContainer.getContainer();
	protected MetaFactoryImpl() {
	}

	protected static MetaFactoryImpl factory;

	@Assignable
	public static MetaFactoryImpl getFactory() {
		if (factory == null) {
			factory = new MetaFactoryImpl();
			factory.loadFactory();
		}
		return factory;
	}

	public MetaFactoryImpl loadFactory() {
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
	public MetaFactoryImpl clear() {
		if (getCache() != null) {
			getCache().clear();
		}
		return this;
	}

	public ClassMeta getMeta(String id) {
		for(Entry<KeyInfo, ClassMeta> entry:getCache().entrySet()) {
			if(entry.getKey().getId().equals(id)) {
				return entry.getValue();
			}
		}
		return getContainer(id);
	}

	public void register(Class<?> target, Model metaSetup) {
		ClassMeta metaInfo = ModelMetaHelper.getModelInfo(getContainer(),target, metaSetup);
		this.getCache().put(metaInfo.getKeyInfo(), metaInfo);
		loadContainer(metaInfo);
	}
	
	public void register(Class<?> target, ClassMetaSetup metaSetup) {
		ClassMeta metaInfo = ModelMetaHelper.getModelInfo(getContainer(),target, metaSetup);
		this.getCache().put(metaInfo.getKeyInfo(), metaInfo);
		loadContainer( metaInfo);
	}
	
	public void loadContainer(ClassMeta metaInfo) {
		if (getContainer() == null) {
			return;
		}
		Group group = getContainer().load(metaInfo.getTarget().getName());
		if(!group.containsKey(metaInfo.getId())) {
			group.add(metaInfo.getId(), metaInfo);
		}else {
			group.update(metaInfo.getId(), metaInfo);
		}
	}
	

	public ClassMeta getContainer(String modelKey) {
		if (getContainer() == null) {
			return null;
		}
		return getContainer().find(modelKey);
	}

	@Override
	public Object groupKey() {
		return null;
	}

	public ModelMeta getModelInfo(Class<?> cls) {
		return null;
	}

}