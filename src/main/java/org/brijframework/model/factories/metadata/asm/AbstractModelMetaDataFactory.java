package org.brijframework.model.factories.metadata.asm;

import java.util.Map.Entry;

import org.brijframework.factories.impl.AbstractFactory;
import org.brijframework.group.Group;
import org.brijframework.model.ModelMetaData;
import org.brijframework.model.factories.MetaFactory;
import org.brijframework.util.printer.ConsolePrint;

public abstract class AbstractModelMetaDataFactory<K,T extends ModelMetaData<?>> extends AbstractFactory<K, T> implements MetaFactory<K, T>{

	@Override
	public AbstractModelMetaDataFactory<K, T> clear() {
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

	public T getMetaInfo(String id) {
		for(Entry<K, T> entry:getCache().entrySet()) {
			if(entry.getKey().equals(id)) {
				return entry.getValue();
			}
		}
		return getContainer(id);
	}
	
	@SuppressWarnings("unchecked")
	public void register(T meta) {
		ConsolePrint.screen("Resource", "Registery for meta data with id : "+meta.getId());
		getCache().put((K)meta.getId(), meta);
		loadContainer(meta);
	}

}
