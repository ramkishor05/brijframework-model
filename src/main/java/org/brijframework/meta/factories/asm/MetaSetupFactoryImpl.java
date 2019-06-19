package org.brijframework.meta.factories.asm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.brijframework.container.Container;
import org.brijframework.group.Group;
import org.brijframework.meta.factories.MetaFactory;
import org.brijframework.meta.helper.MetaBuilderHelper;
import org.brijframework.meta.info.ClassMetaInfo;
import org.brijframework.meta.setup.ClassMetaSetup;
import org.brijframework.support.model.Assignable;
import org.brijframework.support.model.Model;

public class MetaSetupFactoryImpl implements MetaFactory<ClassMetaInfo> {
	
	private ConcurrentHashMap<String, ClassMetaInfo> cache = new ConcurrentHashMap<>();
	
	private Container container;
	
	protected MetaSetupFactoryImpl() {
	}

	protected static MetaSetupFactoryImpl factory;

	@Assignable
	public static MetaSetupFactoryImpl getFactory() {
		if (factory == null) {
			factory = new MetaSetupFactoryImpl();
		}
		return factory;
	}

	public MetaSetupFactoryImpl loadFactory() {
		return this;
	}

	@SuppressWarnings("unchecked")
	public ConcurrentHashMap<String, ClassMetaInfo> getCache() {
		if(cache==null) {
			return null;
		}
		if(getContainer()!=null) {
			for(Entry<Object, Group>  entry:getContainer().getCache().entrySet()) {
				Group  group=entry.getValue();
				if(group!=null)
				group.getCache().forEach((key,value)->{
					cache.put((String)key, (ClassMetaInfo)value);
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
	public MetaSetupFactoryImpl clear() {
		if (getCache() != null) {
			getCache().clear();
		}
		return this;
	}

	public void register(Class<?> target, Model metaSetup) {
		ClassMetaInfo metaInfo = MetaBuilderHelper.getModelInfo(getContainer(),target, metaSetup);
		this.getCache().put(metaInfo.getId(), metaInfo);
		System.err.println("Meta Info    : "+metaInfo.getId());
		loadContainer(metaInfo);
	}
	
	public void register(Class<?> target, ClassMetaSetup metaSetup) {
		ClassMetaInfo metaInfo = MetaBuilderHelper.getModelInfo(getContainer(),target, metaSetup);
		this.getCache().put(metaInfo.getId(), metaInfo);
		System.err.println("Meta Info    : "+metaInfo.getId());
		loadContainer( metaInfo);
	}
	
	public void loadContainer(ClassMetaInfo metaInfo) {
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
	

	public ClassMetaInfo getContainer(String modelKey) {
		if (getContainer() == null) {
			return null;
		}
		return getContainer().find(modelKey);
	}

	public ClassMetaInfo getMeta(String id) {
		for(Entry<String, ClassMetaInfo> entry:getCache().entrySet()) {
			if(entry.getKey().equals(id)) {
				return entry.getValue();
			}
		}
		return getContainer(id);
	}

	public List<ClassMetaInfo> getMetaList(Class<?> model) {
		List<ClassMetaInfo> list=new ArrayList<>();
		for(ClassMetaInfo classMeta:getCache().values()) {
			if(model.isAssignableFrom(classMeta.getTarget())) {
				list.add(classMeta);
			}
		}
		return list;
	}

}