package org.brijframework.model.factories.asm;

import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.brijframework.container.Container;
import org.brijframework.group.Group;
import org.brijframework.model.ModelSetup;
import org.brijframework.model.factories.MetaFactory;

public abstract class MetaSetupFactoryImpl<T extends ModelSetup<?>> implements MetaFactory<T>{
	
	private ConcurrentHashMap<String, T> cache = new ConcurrentHashMap<>();
	
	private Container container;
	
	protected MetaSetupFactoryImpl() {
	}

	@SuppressWarnings("unchecked")
	public ConcurrentHashMap<String, T> getCache() {
		if(cache==null) {
			return null;
		}
		if(getContainer()!=null) {
			for(Entry<Object, Group>  entry:getContainer().getCache().entrySet()) {
				Group  group=entry.getValue();
				if(group!=null)
				group.getCache().forEach((key,value)->{
					cache.put((String)key, (T)value);
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
	public MetaSetupFactoryImpl<T> clear() {
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
		for(Entry<String, T> entry:getCache().entrySet()) {
			if(entry.getKey().equals(id)) {
				return entry.getValue();
			}
		}
		return getContainer(id);
	}

	@Override
	public MetaSetupFactoryImpl<?> loadFactory() {
		return this;
	}
	
	public void register(T metaInfo) {
		loadContainer(metaInfo);
		System.err.println("Meta Setup   : "+metaInfo.getId());
		this.getCache().put(metaInfo.getId(), metaInfo);
	}

}