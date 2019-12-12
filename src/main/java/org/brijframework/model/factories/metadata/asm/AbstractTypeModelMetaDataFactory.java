package org.brijframework.model.factories.metadata.asm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.brijframework.group.Group;
import org.brijframework.model.diffination.PropertyModelMetaDataGroup;
import org.brijframework.model.diffination.TypeModelDiffination;
import org.brijframework.model.factories.asm.AbstractModelMetaDataFactory;
import org.brijframework.model.factories.metadata.TypeModelDiffinationFactory;
import org.brijframework.model.factories.resource.impl.TypeModelResourceFactoryImpl;
import org.brijframework.model.helper.MetaDataHelper;
import org.brijframework.model.resource.TypeModelResource;
import org.brijframework.support.model.Model;
import org.brijframework.util.asserts.Assertion;
import org.brijframework.util.reflect.ClassUtil;

public abstract class AbstractTypeModelMetaDataFactory<K,T extends TypeModelDiffination> extends AbstractModelMetaDataFactory<K, TypeModelDiffination> implements TypeModelDiffinationFactory<K, TypeModelDiffination>{
	
	public TypeModelDiffination getContainer(K modelKey) {
		if (getContainer() == null) {
			return null;
		}
		return  getContainer().find(modelKey);
	}

	@Override
	public void loadContainer(K key, TypeModelDiffination metaInfo) {
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
	
	public TypeModelDiffination findOrCreate(K model) {
		TypeModelDiffination find = find(model);
		if(find!=null) {
			return find;
		}
		TypeModelResource typeModelResource = TypeModelResourceFactoryImpl.getFactory().find((String)model);
		if(typeModelResource==null) {
			return null;
		}
		return register(model,typeModelResource);
	}
	
	public TypeModelDiffination register(String target) {
		Class<?> targetClass =ClassUtil.getClass(target);
		return register(targetClass);
	}

	@SuppressWarnings("unchecked")
	public TypeModelDiffination register(Class<?> targetClass) {
		Assertion.notNull(targetClass, "Target not found for : "+targetClass);
		TypeModelDiffination typeModelMetaData = MetaDataHelper.getModelInfo(getContainer(),targetClass);
		register((K)typeModelMetaData.getId(), typeModelMetaData);
		return typeModelMetaData;
	}
	
	public TypeModelDiffination register(K key, TypeModelResource metaSetup) {
		Class<?> target=ClassUtil.getClass(metaSetup.getType());
		Assertion.notNull(target, "Target class not found for "+metaSetup.getId());;
		TypeModelDiffination classMetaInfo=MetaDataHelper.getModelInfo(getContainer(), target, metaSetup);
		return register(key,classMetaInfo);
	}
	
	@SuppressWarnings("unchecked")
	public void register(Class<?> target, TypeModelResource metaSetup) {
		TypeModelDiffination typeModelMetaData = MetaDataHelper.getModelInfo(getContainer(),target, metaSetup);
		register((K)typeModelMetaData.getId(),typeModelMetaData);
	}
	
	@SuppressWarnings("unchecked")
	public void register(Class<?> target, Model metaSetup) {
		TypeModelDiffination typeModelMetaData = MetaDataHelper.getModelInfo(getContainer(),target, metaSetup);
		register((K)typeModelMetaData.getId(), typeModelMetaData);
	}
	
	@SuppressWarnings("unchecked")
	public TypeModelDiffination load(Class<?> targetClass) {
		TypeModelDiffination metaInfo =find((K)targetClass.getSimpleName());
		if(metaInfo!=null) {
			return metaInfo;
		}
		return register(targetClass);
	}

	public List<TypeModelDiffination> findByType(Class<?> model) {
		List<TypeModelDiffination> list=new ArrayList<>();
		for(TypeModelDiffination meta:getCache().values()) {
			if(model.isAssignableFrom(meta.getType())) {
				list.add(meta);
			}
		}
		return list;
	}
	
	public TypeModelDiffination findById(K id) {
		for(Entry<K, TypeModelDiffination> entry:getCache().entrySet()) {
			if(entry.getKey().equals(id)) {
				return entry.getValue();
			}
		}
		return getContainer(id);
	}
	
	public PropertyModelMetaDataGroup getPropertyMetaData(K typeId, String _keyPath) {
		TypeModelDiffination classMeta=findById(typeId);
		if(classMeta==null) {
			return null;
		}
		return classMeta.getProperty(_keyPath);
	}
}
