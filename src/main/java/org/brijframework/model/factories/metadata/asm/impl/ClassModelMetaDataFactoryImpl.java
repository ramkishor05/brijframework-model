package org.brijframework.model.factories.metadata.asm.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.brijframework.group.Group;
import org.brijframework.model.factories.metadata.ClassMetaDataFactory;
import org.brijframework.model.factories.metadata.asm.ModelMetaDataFactoryImpl;
import org.brijframework.model.factories.resource.asm.impl.ClassModelResourceFactoryImpl;
import org.brijframework.model.helper.MetaDataHelper;
import org.brijframework.model.info.ClassModelMetaData;
import org.brijframework.model.info.PropertyModelMetaDataGroup;
import org.brijframework.model.resource.ClassModelResource;
import org.brijframework.support.config.Assignable;
import org.brijframework.support.model.Model;
import org.brijframework.util.asserts.Assertion;
import org.brijframework.util.reflect.ClassUtil;

public class ClassModelMetaDataFactoryImpl extends ModelMetaDataFactoryImpl<String,ClassModelMetaData> implements ClassMetaDataFactory {
	
	protected ClassModelMetaDataFactoryImpl() {
	}

	protected static ClassModelMetaDataFactoryImpl factory;

	@Assignable
	public static ClassModelMetaDataFactoryImpl getFactory() {
		if (factory == null) {
			factory = new ClassModelMetaDataFactoryImpl();
		}
		return factory;
	}


	@Override
	public ClassModelMetaDataFactoryImpl loadFactory() {
		ClassModelResourceFactoryImpl.getFactory().getCache().forEach((key,metaSetup)->{
			register(key, metaSetup);
		});
		return this;
	}

	
	public void register(String key, ClassModelResource metaSetup) {
		Class<?> target=ClassUtil.getClass(metaSetup.getTarget());
		Assertion.notNull(target, "Target class not found for "+metaSetup.getId());;
		ClassModelMetaData classMetaInfo=MetaDataHelper.getModelInfo(getContainer(), target, metaSetup);
		register(classMetaInfo);
	}

	@Override
	public ClassModelMetaDataFactoryImpl clear() {
		if (getCache() != null) {
			getCache().clear();
		}
		return this;
	}

	public ClassModelMetaData getClassInfo(String id) {
		for(Entry<String, ClassModelMetaData> entry:getCache().entrySet()) {
			if(entry.getKey().equals(id)) {
				return entry.getValue();
			}
		}
		return getContainer(id);
	}

	/*public void register(Class<?> target, OwnerModelInfo metaInfo) {
		ConsolePrint.screen("Meta data", "Registery meta data");
		loadContainer(metaInfo);
		System.err.println("Model Info    : "+metaInfo.getId());
		this.getCache().put(metaInfo.getId(), metaInfo);
	}*/
	
	public void loadContainer(ClassModelMetaData metaInfo) {
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

	public ClassModelMetaData getContainer(String modelKey) {
		if (getContainer() == null) {
			return null;
		}
		return  getContainer().find(modelKey);
	}

	@Override
	public List<ClassModelMetaData> getClassInfoList(Class<?> cls) {
		return null;
	}

	@Override
	public List<ClassModelMetaData> getClassInfoList(Class<?> target, String parentID) {
		return null;
	}

	public PropertyModelMetaDataGroup getFieldMeta(String simpleName, String _keyPath) {
		ClassModelMetaData classMeta=getClassInfo(simpleName);
		if(classMeta==null) {
			return null;
		}
		return classMeta.getProperty(_keyPath);
	}

	public void register(Class<?> target, Model metaSetup) {
		ClassModelMetaData metaInfo = MetaDataHelper.getModelInfo(getContainer(),target, metaSetup);
		this.getCache().put(metaInfo.getId(), metaInfo);
		System.err.println("Meta Info    : "+metaInfo.getId());
		loadContainer(metaInfo);
	}
	
	public void register(Class<?> target, ClassModelResource metaSetup) {
		ClassModelMetaData metaInfo = MetaDataHelper.getModelInfo(getContainer(),target, metaSetup);
		this.getCache().put(metaInfo.getId(), metaInfo);
		loadContainer( metaInfo);
		System.err.println("Registered Meta Info                     : "+metaInfo.getId());
	}
	

	public List<ClassModelMetaData> getMetaList(Class<?> model) {
		List<ClassModelMetaData> list=new ArrayList<>();
		for(ClassModelMetaData meta:getCache().values()) {
			if(model.isAssignableFrom(meta.getTarget())) {
				list.add(meta);
			}
		}
		return list;
	}

	public ClassModelMetaData register(String target) {
		Class<?> targetClass =ClassUtil.getClass(target);
		return register(targetClass);
	}

	public ClassModelMetaData register(Class<?> targetClass) {
		Assertion.notNull(targetClass, "Target not found for : "+targetClass);
		ClassModelMetaData metaInfo = MetaDataHelper.getModelInfo(getContainer(),targetClass);
		this.getCache().put(metaInfo.getId(), metaInfo);
		loadContainer( metaInfo);
		System.err.println("Registered Meta Info                     : "+metaInfo.getId());
		return metaInfo;
	}

	public ClassModelMetaData load(Class<?> targetClass) {
		ClassModelMetaData metaInfo =getClassInfo(targetClass.getSimpleName());
		if(metaInfo!=null) {
			return metaInfo;
		}
		return register(targetClass);
	}


	@Override
	protected void preregister(String key, ClassModelMetaData value) {
		
	}


	@Override
	protected void postregister(String key, ClassModelMetaData value) {
		
	}
	
}