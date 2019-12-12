package org.brijframework.model.factories.asm;

import java.util.Map.Entry;

import org.brijframework.factories.impl.AbstractFactory;
import org.brijframework.group.Group;
import org.brijframework.model.ModelDiffination;
import org.brijframework.model.factories.ModelDiffinationFactory;
import org.brijframework.util.printer.LoggerConsole;

public abstract class AbstractModelMetaDataFactory<K, T extends ModelDiffination<?>> extends AbstractFactory<K, T> implements ModelDiffinationFactory<K, T> {
	
	@Override
	public AbstractModelMetaDataFactory<K, T> clear() {
		if (getCache() != null) {
			getCache().clear();
		}
		return this;
	}

	public void loadContainer(K key,T metaInfo) {
		if (getContainer() == null) {
			return;
		}
		Group group = getContainer().load(metaInfo.getName());
		if(!group.containsKey(key)) {
			group.add(key, metaInfo);
		}else {
			group.update(key, metaInfo);
		}
	}

	public T getContainer(K modelKey) {
		if (getContainer() == null) {
			return null;
		}
		return getContainer().find(modelKey);
	}

	public T getMetaInfo(K id) {
		for(Entry<K, T> entry:getCache().entrySet()) {
			if(entry.getKey().equals(id)) {
				return entry.getValue();
			}
		}
		return getContainer(id);
	}
	
	@SuppressWarnings("unchecked")
	public void register(T value) {
		K key=(K)value.getId();
		preregister(key, value);
		getCache().put(key, value);
		loadContainer(key, value);
		postregister(key, value);
	}

	@Override
	protected void preregister(K key, T value) {
		LoggerConsole.screen("ModelMeta", "Registering for meta data with id : "+key);
	}
	
	@Override
	protected void postregister(K key, T value) {
		LoggerConsole.screen("ModelMeta", "Registered for meta data with id : "+key);
	}
}
