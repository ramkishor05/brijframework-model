package org.brijframework.model.factories.asm;

import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.brijframework.container.Container;
import org.brijframework.factories.impl.AbstractFactory;
import org.brijframework.group.Group;
import org.brijframework.model.ModelResource;
import org.brijframework.model.factories.MetaFactory;
import org.brijframework.util.printer.ConsolePrint;

public abstract class MetaSetupFactoryImpl<K, T extends ModelResource<?>> extends AbstractFactory<K, T> implements MetaFactory<K, T>{
	
	private ConcurrentHashMap<K, T> cache = new ConcurrentHashMap<>();
	
	private Container container;
	
	protected MetaSetupFactoryImpl() {
	}

	@SuppressWarnings("unchecked")
	public ConcurrentHashMap<K, T> getCache() {
		if(cache==null) {
			return null;
		}
		if(getContainer()!=null) {
			for(Entry<Object, Group>  entry:getContainer().getCache().entrySet()) {
				Group  group=entry.getValue();
				if(group!=null)
				group.getCache().forEach((key,value)->{
					cache.put((K)key, (T)value);
				});
			}
		}
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
	public MetaSetupFactoryImpl<K, T> clear() {
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

	@Override
	public MetaSetupFactoryImpl<K, T> loadFactory() {
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public void register(T metaInfo) {
		ConsolePrint.screen("Resource", "Registery for meta resource with id : "+metaInfo.getId());
		loadContainer(metaInfo);
		this.getCache().put((K)metaInfo.getId(), metaInfo);
	}

}