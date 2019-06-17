package org.brijframework.meta.factories.asm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.brijframework.container.Container;
import org.brijframework.group.Group;
import org.brijframework.meta.KeyInfo;
import org.brijframework.meta.factories.MetaFactory;
import org.brijframework.meta.helper.ModelMetaHelper;
import org.brijframework.meta.reflect.ClassMeta;
import org.brijframework.meta.setup.ClassMetaSetup;
import org.brijframework.support.model.Assignable;
import org.brijframework.support.model.Model;

public class MetaFactoryImpl implements MetaFactory<ClassMeta> {
	public final static String MODELS = "MODELS";
	private ConcurrentHashMap<KeyInfo, ClassMeta> cache = new ConcurrentHashMap<>();
	
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
	public ConcurrentHashMap<KeyInfo, ClassMeta> getCache() {
		if(cache==null) {
			return null;
		}
		if(getContainer()!=null) {
			Group  group=getContainer().get(MODELS);
			if(group!=null)
			group.getCache().forEach((key,value)->{
				cache.put((KeyInfo)key, (ClassMeta)value);
			});
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
		ClassMeta metaInfo = ModelMetaHelper.getModelInfo(getContainer(),target, metaSetup);
		this.getCache().put(metaInfo.getKeyInfo(), metaInfo);
		System.err.println("Meta Info    : "+metaInfo.getKeyInfo());
		loadContainer(metaInfo);
	}
	
	public void register(Class<?> target, ClassMetaSetup metaSetup) {
		ClassMeta metaInfo = ModelMetaHelper.getModelInfo(getContainer(),target, metaSetup);
		this.getCache().put(metaInfo.getKeyInfo(), metaInfo);
		System.err.println("Meta Info    : "+metaInfo.getKeyInfo());
		loadContainer( metaInfo);
	}
	
	public void loadContainer(ClassMeta metaInfo) {
		if (getContainer() == null) {
			return;
		}
		Group group = getContainer().load(MODELS);
		if(!group.containsKey(metaInfo.getKeyInfo())) {
			group.add(metaInfo.getKeyInfo(), metaInfo);
		}else {
			group.update(metaInfo.getKeyInfo(), metaInfo);
		}
	}
	

	public ClassMeta getContainer(String modelKey) {
		if (getContainer() == null) {
			return null;
		}
		Group group=getContainer().get(MODELS);
		if(group==null) {
			return null;
		}
		return group.find(modelKey);
	}

	@Override
	public Object groupKey() {
		return null;
	}
	

	public ClassMeta getMeta(String id) {
		for(Entry<KeyInfo, ClassMeta> entry:getCache().entrySet()) {
			if(entry.getKey().getId().equals(id)) {
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