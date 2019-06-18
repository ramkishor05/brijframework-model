package org.brijframework.meta.factories.asm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.brijframework.container.Container;
import org.brijframework.group.Group;
import org.brijframework.meta.factories.MetaFactory;
import org.brijframework.meta.helper.MetaHelper;
import org.brijframework.meta.reflect.ClassMeta;
import org.brijframework.meta.setup.ClassMetaSetup;
import org.brijframework.support.model.Assignable;
import org.brijframework.support.model.Model;

public class MetaFactoryImpl implements MetaFactory<ClassMeta> {
	
	private ConcurrentHashMap<String, ClassMeta> cache = new ConcurrentHashMap<>();
	
	private Container container;
	
	protected MetaFactoryImpl() {
	}

	protected static MetaFactoryImpl factory;

	@Assignable
	public static MetaFactoryImpl getFactory() {
		if (factory == null) {
			factory = new MetaFactoryImpl();
		}
		return factory;
	}

	public MetaFactoryImpl loadFactory() {
		return this;
	}

	@SuppressWarnings("unchecked")
	public ConcurrentHashMap<String, ClassMeta> getCache() {
		if(cache==null) {
			return null;
		}
		if(getContainer()!=null) {
			for(Entry<Object, Group>  entry:getContainer().getCache().entrySet()) {
				Group  group=entry.getValue();
				if(group!=null)
				group.getCache().forEach((key,value)->{
					cache.put((String)key, (ClassMeta)value);
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
	public MetaFactoryImpl clear() {
		if (getCache() != null) {
			getCache().clear();
		}
		return this;
	}

	public void register(Class<?> target, Model metaSetup) {
		ClassMeta metaInfo = MetaHelper.getModelInfo(getContainer(),target, metaSetup);
		this.getCache().put(metaInfo.getId(), metaInfo);
		System.err.println("Meta Info    : "+metaInfo.getId());
		loadContainer(metaInfo);
	}
	
	public void register(Class<?> target, ClassMetaSetup metaSetup) {
		ClassMeta metaInfo = MetaHelper.getModelInfo(getContainer(),target, metaSetup);
		this.getCache().put(metaInfo.getId(), metaInfo);
		System.err.println("Meta Info    : "+metaInfo.getId());
		loadContainer( metaInfo);
	}
	
	public void loadContainer(ClassMeta metaInfo) {
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
	

	public ClassMeta getContainer(String modelKey) {
		if (getContainer() == null) {
			return null;
		}
		return getContainer().find(modelKey);
	}

	public ClassMeta getMeta(String id) {
		for(Entry<String, ClassMeta> entry:getCache().entrySet()) {
			if(entry.getKey().equals(id)) {
				return entry.getValue();
			}
		}
		return getContainer(id);
	}

	public List<ClassMeta> getMetaList(Class<?> model) {
		List<ClassMeta> list=new ArrayList<>();
		for(ClassMeta classMeta:getCache().values()) {
			if(model.isAssignableFrom(classMeta.getTarget())) {
				list.add(classMeta);
			}
		}
		return list;
	}

}