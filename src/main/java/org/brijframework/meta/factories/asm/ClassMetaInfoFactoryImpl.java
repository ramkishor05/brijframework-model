package org.brijframework.meta.factories.asm;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.brijframework.container.Container;
import org.brijframework.group.Group;
import org.brijframework.meta.KeyInfo;
import org.brijframework.meta.asm.annotation.ModelMetaInfo;
import org.brijframework.meta.container.MetaContainer;
import org.brijframework.meta.factories.ClassMetaInfoFactory;
import org.brijframework.meta.reflect.ClassMetaInfo;
import org.brijframework.support.model.Assignable;

public class ClassMetaInfoFactoryImpl implements ClassMetaInfoFactory {
	
	public static final String MODELES = "MODELES";
	
	private ConcurrentHashMap<KeyInfo, ClassMetaInfo> cache = new ConcurrentHashMap<>();
	
	private Container container = MetaContainer.getContainer();
	
	protected ClassMetaInfoFactoryImpl() {
	}

	protected static ClassMetaInfoFactoryImpl factory;

	@Assignable
	public static ClassMetaInfoFactoryImpl getFactory() {
		if (factory == null) {
			factory = new ClassMetaInfoFactoryImpl();
			factory.loadFactory();
		}
		return factory;
	}

	@SuppressWarnings("unchecked")
	public ClassMetaInfoFactoryImpl loadFactory() {
		this.clear();
		if (getContainer() != null) {
			Group group = getContainer().get(MODELES);
			if(group!=null)
			getCache().putAll(group.getCache());
		}
		return this;
	}

	public ConcurrentHashMap<KeyInfo, ClassMetaInfo> getCache() {
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
	public ClassMetaInfoFactoryImpl clear() {
		if (getCache() != null) {
			getCache().clear();
		}
		return this;
	}

	public ClassMetaInfo getClassInfo(String id) {
		ClassMetaInfo info=getCache().get(id);
		if(info!=null) {
			return info;
		}
		return getContainer(MODELES, id);
	}

	public void register(Class<?> target, ClassMetaInfo metaInfo) {
		this.getCache().put(metaInfo.getKeyInfo(), metaInfo);
		loadContainer(MODELES, metaInfo);
	}
	
	public void loadContainer(String groupKey, ClassMetaInfo metaInfo) {
		if (getContainer() == null) {
			return;
		}
		Group group = getContainer().load(groupKey);
		if(!group.containsKey(metaInfo.getId())) {
			group.add(metaInfo.getId(), metaInfo);
		}else {
			group.update(metaInfo.getId(), metaInfo);
		}
		getContainer().merge(groupKey, group);
	}

	public ModelMetaInfo getContainer(String groupKey, String modelKey) {
		if (getContainer() == null) {
			return null;
		}
		Group group = getContainer().load(groupKey);
		if(group!=null) {
			return group.get(modelKey);
		}
		return null;
	}

	@Override
	public Object groupKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ClassMetaInfo> getClassInfoList(Class<?> cls) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ClassMetaInfo> getClassInfoList(Class<?> target, String parentID) {
		// TODO Auto-generated method stub
		return null;
	}

}