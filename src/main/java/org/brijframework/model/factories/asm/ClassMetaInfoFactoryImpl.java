package org.brijframework.model.factories.asm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.brijframework.group.Group;
import org.brijframework.model.factories.ClassMetaInfoFactory;
import org.brijframework.model.helper.MetaInfoHelper;
import org.brijframework.model.info.OwnerModelInfo;
import org.brijframework.model.info.PptModelInfo;
import org.brijframework.model.setup.ClassMetaSetup;
import org.brijframework.support.config.Assignable;
import org.brijframework.support.model.Model;
import org.brijframework.util.asserts.Assertion;
import org.brijframework.util.reflect.ClassUtil;

public class ClassMetaInfoFactoryImpl extends MetaInfoFactoryImpl<OwnerModelInfo> implements ClassMetaInfoFactory {
	
	protected ClassMetaInfoFactoryImpl() {
	}

	protected static ClassMetaInfoFactoryImpl factory;

	@Assignable
	public static ClassMetaInfoFactoryImpl getFactory() {
		if (factory == null) {
			factory = new ClassMetaInfoFactoryImpl();
		}
		return factory;
	}


	@Override
	public ClassMetaInfoFactoryImpl loadFactory() {
		ClassMetaSetupFactoryImpl.getFactory().getCache().forEach((key,metaSetup)->{
			register(key, metaSetup);
		});
		return this;
	}

	
	public void register(String key, ClassMetaSetup metaSetup) {
		Class<?> target=ClassUtil.getClass(metaSetup.getTarget());
		Assertion.notNull(target, "Target class not found for "+metaSetup.getId());;
		OwnerModelInfo classMetaInfo=MetaInfoHelper.getModelInfo(getContainer(), target, metaSetup);
		register(target, classMetaInfo);
	}

	@Override
	public ClassMetaInfoFactoryImpl clear() {
		if (getCache() != null) {
			getCache().clear();
		}
		return this;
	}

	public OwnerModelInfo getClassInfo(String id) {
		for(Entry<String, OwnerModelInfo> entry:getCache().entrySet()) {
			if(entry.getKey().equals(id)) {
				return entry.getValue();
			}
		}
		return getContainer(id);
	}

	public void register(Class<?> target, OwnerModelInfo metaInfo) {
		loadContainer(metaInfo);
		System.err.println("Model Info    : "+metaInfo.getId());
		this.getCache().put(metaInfo.getId(), metaInfo);
	}
	
	public void loadContainer(OwnerModelInfo metaInfo) {
		if (getContainer() == null) {
			return;
		}
		Group group = getContainer().load(metaInfo.getName());
		if(!group.containsKey(metaInfo.getId())) {
			group.add(metaInfo.getId(), metaInfo);
		}else {
			group.update(metaInfo.getId(), metaInfo);
		}
		getContainer().merge(metaInfo.getName(), group);
	}

	public OwnerModelInfo getContainer(String modelKey) {
		if (getContainer() == null) {
			return null;
		}
		return  getContainer().find(modelKey);
	}

	@Override
	public List<OwnerModelInfo> getClassInfoList(Class<?> cls) {
		return null;
	}

	@Override
	public List<OwnerModelInfo> getClassInfoList(Class<?> target, String parentID) {
		return null;
	}

	public PptModelInfo getFieldMeta(String simpleName, String _keyPath) {
		OwnerModelInfo classMeta=getClassInfo(simpleName);
		if(classMeta==null) {
			return null;
		}
		return classMeta.getPropertyInfo(_keyPath);
	}

	public void register(Class<?> target, Model metaSetup) {
		OwnerModelInfo metaInfo = MetaInfoHelper.getModelInfo(getContainer(),target, metaSetup);
		this.getCache().put(metaInfo.getId(), metaInfo);
		System.err.println("Meta Info    : "+metaInfo.getId());
		loadContainer(metaInfo);
	}
	
	public void register(Class<?> target, ClassMetaSetup metaSetup) {
		OwnerModelInfo metaInfo = MetaInfoHelper.getModelInfo(getContainer(),target, metaSetup);
		this.getCache().put(metaInfo.getId(), metaInfo);
		loadContainer( metaInfo);
		System.err.println("Registered Meta Info                     : "+metaInfo.getId());
	}
	

	public List<OwnerModelInfo> getMetaList(Class<?> model) {
		List<OwnerModelInfo> list=new ArrayList<>();
		for(OwnerModelInfo meta:getCache().values()) {
			if(model.isAssignableFrom(meta.getTarget())) {
				list.add(meta);
			}
		}
		return list;
	}

	public OwnerModelInfo register(String target) {
		Class<?> targetClass =ClassUtil.getClass(target);
		return register(targetClass);
	}

	public OwnerModelInfo register(Class<?> targetClass) {
		Assertion.notNull(targetClass, "Target not found for : "+targetClass);
		OwnerModelInfo metaInfo = MetaInfoHelper.getModelInfo(getContainer(),targetClass);
		this.getCache().put(metaInfo.getId(), metaInfo);
		loadContainer( metaInfo);
		System.err.println("Registered Meta Info                     : "+metaInfo.getId());
		return metaInfo;
	}

	public OwnerModelInfo load(Class<?> targetClass) {
		OwnerModelInfo metaInfo =getClassInfo(targetClass.getSimpleName());
		if(metaInfo!=null) {
			return metaInfo;
		}
		return register(targetClass);
	}
	
}