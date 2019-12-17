package org.brijframework.model.factories.metadata.asm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.brijframework.group.Group;
import org.brijframework.model.diffination.ModelPropertyDiffinationGroup;
import org.brijframework.model.diffination.ModelTypeDiffination;
import org.brijframework.model.factories.asm.AbstractModelMetaDataFactory;
import org.brijframework.model.factories.metadata.TypeModelDiffinationFactory;
import org.brijframework.model.factories.resource.impl.TypeModelResourceFactoryImpl;
import org.brijframework.model.helper.MetaDataHelper;
import org.brijframework.model.resource.TypeModelResource;
import org.brijframework.support.model.Model;
import org.brijframework.util.asserts.Assertion;
import org.brijframework.util.reflect.ClassUtil;

public abstract class AbstractTypeModelMetaDataFactory<K,T extends ModelTypeDiffination> extends AbstractModelMetaDataFactory<K, ModelTypeDiffination> implements TypeModelDiffinationFactory<K, ModelTypeDiffination>{
	
	public ModelTypeDiffination getContainer(K modelKey) {
		if (getContainer() == null) {
			return null;
		}
		return  getContainer().find(modelKey);
	}

	@Override
	public void loadContainer(K key, ModelTypeDiffination metaInfo) {
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
	
	public ModelTypeDiffination findOrCreate(K model) {
		ModelTypeDiffination find = find(model);
		if(find!=null) {
			return find;
		}
		TypeModelResource typeModelResource = TypeModelResourceFactoryImpl.getFactory().find((String)model);
		if(typeModelResource==null) {
			return null;
		}
		return register(model,typeModelResource);
	}
	
	public ModelTypeDiffination register(String target) {
		Class<?> targetClass =ClassUtil.getClass(target);
		return register(targetClass);
	}

	@SuppressWarnings("unchecked")
	public ModelTypeDiffination register(Class<?> targetClass) {
		Assertion.notNull(targetClass, "Target not found for : "+targetClass);
		ModelTypeDiffination typeModelMetaData = MetaDataHelper.getModelInfo(getContainer(),targetClass);
		register((K)typeModelMetaData.getId(), typeModelMetaData);
		return typeModelMetaData;
	}
	
	public ModelTypeDiffination register(K key, TypeModelResource metaSetup) {
		Class<?> target=ClassUtil.getClass(metaSetup.getType());
		Assertion.notNull(target, "Target class not found for "+metaSetup.getId());;
		ModelTypeDiffination classMetaInfo=MetaDataHelper.getModelInfo(getContainer(), target, metaSetup);
		return register(key,classMetaInfo);
	}
	
	@SuppressWarnings("unchecked")
	public void register(Class<?> target, TypeModelResource metaSetup) {
		ModelTypeDiffination typeModelMetaData = MetaDataHelper.getModelInfo(getContainer(),target, metaSetup);
		register((K)typeModelMetaData.getId(),typeModelMetaData);
	}
	
	@SuppressWarnings("unchecked")
	public void register(Class<?> target, Model metaSetup) {
		ModelTypeDiffination typeModelMetaData = MetaDataHelper.getModelInfo(getContainer(),target, metaSetup);
		register((K)typeModelMetaData.getId(), typeModelMetaData);
	}
	
	@SuppressWarnings("unchecked")
	public ModelTypeDiffination load(Class<?> targetClass) {
		ModelTypeDiffination metaInfo =find((K)targetClass.getSimpleName());
		if(metaInfo!=null) {
			return metaInfo;
		}
		return register(targetClass);
	}

	public List<ModelTypeDiffination> findByType(Class<?> model) {
		List<ModelTypeDiffination> list=new ArrayList<>();
		for(ModelTypeDiffination meta:getCache().values()) {
			if(model.isAssignableFrom(meta.getType())) {
				list.add(meta);
			}
		}
		return list;
	}
	
	public ModelTypeDiffination findById(K id) {
		for(Entry<K, ModelTypeDiffination> entry:getCache().entrySet()) {
			if(entry.getKey().equals(id)) {
				return entry.getValue();
			}
		}
		return getContainer(id);
	}
	
	public ModelPropertyDiffinationGroup getPropertyMetaData(K typeId, String _keyPath) {
		ModelTypeDiffination classMeta=findById(typeId);
		if(classMeta==null) {
			return null;
		}
		return classMeta.getProperty(_keyPath);
	}
}
