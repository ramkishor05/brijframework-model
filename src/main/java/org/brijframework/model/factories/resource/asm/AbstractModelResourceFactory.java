package org.brijframework.model.factories.resource.asm;

import java.util.Map.Entry;

import org.brijframework.factories.impl.AbstractFactory;
import org.brijframework.group.Group;
import org.brijframework.model.ModelResource;
import org.brijframework.model.factories.MetaFactory;
import org.brijframework.util.printer.ConsolePrint;

public abstract class AbstractModelResourceFactory<K, T extends ModelResource<?>> extends AbstractFactory<K, T> implements MetaFactory<K, T>{
	
	@Override
	public AbstractModelResourceFactory<K, T> clear() {
		if (getCache() != null) {
			getCache().clear();
		}
		return this;
	}

	public void loadContainer(T metaInfo) {
		if (getContainer() == null) {
			return;
		}
		Group group = getContainer().load(metaInfo.getName());
		if(!group.containsKey(metaInfo.getId())) {
			group.add(metaInfo.getId(), metaInfo);
		}else {
			group.update(metaInfo.getId(), metaInfo);
		}
	}

	public T getContainer(String modelKey) {
		if (getContainer() == null) {
			return null;
		}
		return getContainer().find(modelKey);
	}

	public T getMeta(String id) {
		for(Entry<K, T> entry:getCache().entrySet()) {
			if(entry.getKey().equals(id)) {
				return entry.getValue();
			}
		}
		return getContainer(id);
	}

	@SuppressWarnings("unchecked")
	public void register(T metaInfo) {
		ConsolePrint.screen("Resource", "Registery for meta resource with id : "+metaInfo.getId());
		loadContainer(metaInfo);
		this.getCache().put((K)metaInfo.getId(), metaInfo);
	}
	
	@Override
	protected void preregister(K key, T value) {
	}

	@Override
	protected void postregister(K key, T value) {
	}


}