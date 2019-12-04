package org.brijframework.model.factories.metadata.asm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.brijframework.group.Group;
import org.brijframework.model.factories.asm.AbstractModelMetaDataFactory;
import org.brijframework.model.factories.metadata.TypeModelMetaDataFactory;
import org.brijframework.model.helper.MetaDataHelper;
import org.brijframework.model.metadata.PropertyModelMetaDataGroup;
import org.brijframework.model.metadata.TypeModelMetaData;
import org.brijframework.model.resource.TypeModelResource;
import org.brijframework.support.model.Model;
import org.brijframework.util.asserts.Assertion;
import org.brijframework.util.reflect.ClassUtil;

public abstract class AbstractTypeModelMetaDataFactory<K,T extends TypeModelMetaData> extends AbstractModelMetaDataFactory<K, TypeModelMetaData> implements TypeModelMetaDataFactory<K, TypeModelMetaData>{
	
	public TypeModelMetaData getContainer(K modelKey) {
		if (getContainer() == null) {
			return null;
		}
		return  getContainer().find(modelKey);
	}

	@Override
	public void loadContainer(K key, TypeModelMetaData metaInfo) {
		if (getContainer() == null) {
			return;
		}
		Group group = getContainer().load(metaInfo.getName());
		if(!group.containsKey(key)) {
			group.add(key, metaInfo);
		}else {
			group.update(key, metaInfo);
		}
		getContainer().merge(metaInfo.getName(), group);
	}
	
	public TypeModelMetaData register(String target) {
		Class<?> targetClass =ClassUtil.getClass(target);
		return register(targetClass);
	}

	@SuppressWarnings("unchecked")
	public TypeModelMetaData register(Class<?> targetClass) {
		Assertion.notNull(targetClass, "Target not found for : "+targetClass);
		TypeModelMetaData typeModelMetaData = MetaDataHelper.getModelInfo(getContainer(),targetClass);
		register((K)typeModelMetaData.getId(), typeModelMetaData);
		return typeModelMetaData;
	}
	
	public void register(K key, TypeModelResource metaSetup) {
		Class<?> target=ClassUtil.getClass(metaSetup.getType());
		Assertion.notNull(target, "Target class not found for "+metaSetup.getId());;
		TypeModelMetaData classMetaInfo=MetaDataHelper.getModelInfo(getContainer(), target, metaSetup);
		register(key,classMetaInfo);
	}
	
	@SuppressWarnings("unchecked")
	public void register(Class<?> target, TypeModelResource metaSetup) {
		TypeModelMetaData typeModelMetaData = MetaDataHelper.getModelInfo(getContainer(),target, metaSetup);
		register((K)typeModelMetaData.getId(),typeModelMetaData);
	}
	
	@SuppressWarnings("unchecked")
	public void register(Class<?> target, Model metaSetup) {
		TypeModelMetaData typeModelMetaData = MetaDataHelper.getModelInfo(getContainer(),target, metaSetup);
		register((K)typeModelMetaData.getId(), typeModelMetaData);
	}
	
	@SuppressWarnings("unchecked")
	public TypeModelMetaData load(Class<?> targetClass) {
		TypeModelMetaData metaInfo =find((K)targetClass.getSimpleName());
		if(metaInfo!=null) {
			return metaInfo;
		}
		return register(targetClass);
	}

	public List<TypeModelMetaData> findByType(Class<?> model) {
		List<TypeModelMetaData> list=new ArrayList<>();
		for(TypeModelMetaData meta:getCache().values()) {
			if(model.isAssignableFrom(meta.getType())) {
				list.add(meta);
			}
		}
		return list;
	}
	
	public TypeModelMetaData findById(K id) {
		for(Entry<K, TypeModelMetaData> entry:getCache().entrySet()) {
			if(entry.getKey().equals(id)) {
				return entry.getValue();
			}
		}
		return getContainer(id);
	}
	
	public PropertyModelMetaDataGroup getPropertyMetaData(K typeId, String _keyPath) {
		TypeModelMetaData classMeta=findById(typeId);
		if(classMeta==null) {
			return null;
		}
		return classMeta.getProperty(_keyPath);
	}
}
