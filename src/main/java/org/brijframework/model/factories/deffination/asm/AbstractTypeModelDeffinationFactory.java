package org.brijframework.model.factories.deffination.asm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.brijframework.group.Group;
import org.brijframework.model.diffination.ModelPropertyDiffinationGroup;
import org.brijframework.model.diffination.ModelTypeDeffination;
import org.brijframework.model.factories.asm.AbstractModelDeffinationFactory;
import org.brijframework.model.factories.deffination.TypeModelDiffinationFactory;
import org.brijframework.model.factories.resource.impl.DefaultTypeModelResourceFactory;
import org.brijframework.model.helper.MetaDataHelper;
import org.brijframework.model.resource.TypeModelResource;
import org.brijframework.support.model.Model;
import org.brijframework.util.asserts.Assertion;
import org.brijframework.util.printer.LoggerConsole;
import org.brijframework.util.reflect.ClassUtil;

public abstract class AbstractTypeModelDeffinationFactory<K,T extends ModelTypeDeffination> extends AbstractModelDeffinationFactory<K, ModelTypeDeffination> implements TypeModelDiffinationFactory<K, ModelTypeDeffination>{
	
	public ModelTypeDeffination getContainer(K modelKey) {
		if (getContainer() == null) {
			return null;
		}
		return  getContainer().find(modelKey);
	}

	@Override
	public void loadContainer(K key, ModelTypeDeffination metaInfo) {
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
	
	public ModelTypeDeffination findOrCreate(K model) {
		ModelTypeDeffination find = find(model);
		if(find!=null) {
			return find;
		}
		TypeModelResource typeModelResource = DefaultTypeModelResourceFactory.getFactory().find((String)model);
		if(typeModelResource==null) {
			return null;
		}
		return register(model,typeModelResource);
	}
	
	public ModelTypeDeffination register(String target) {
		Class<?> targetClass =ClassUtil.getClass(target);
		return register(targetClass);
	}

	@SuppressWarnings("unchecked")
	public ModelTypeDeffination register(Class<?> targetClass) {
		Assertion.notNull(targetClass, "Target not found for : "+targetClass);
		ModelTypeDeffination typeModelMetaData = MetaDataHelper.getModelInfo(getContainer(),targetClass);
		register((K)typeModelMetaData.getId(), typeModelMetaData);
		return typeModelMetaData;
	}
	
	public ModelTypeDeffination register(K key, TypeModelResource metaSetup) {
		Class<?> target=ClassUtil.getClass(metaSetup.getType());
		Assertion.notNull(target, "Target class not found for "+metaSetup.getId());;
		ModelTypeDeffination classMetaInfo= MetaDataHelper.getModelTypeDiffination(getContainer(), target, metaSetup);
		return register(key,classMetaInfo);
	}
	
	@SuppressWarnings("unchecked")
	public void register(Class<?> target, TypeModelResource metaSetup) {
		ModelTypeDeffination typeModelMetaData = MetaDataHelper.getModelTypeDiffination(getContainer(),target, metaSetup);
		register((K)typeModelMetaData.getId(),typeModelMetaData);
	}
	
	@SuppressWarnings("unchecked")
	public void register(Class<?> target, Model metaSetup) {
		ModelTypeDeffination typeModelMetaData = MetaDataHelper.getModelInfo(getContainer(),target, metaSetup);
		register((K)typeModelMetaData.getId(), typeModelMetaData);
	}
	
	@SuppressWarnings("unchecked")
	public ModelTypeDeffination load(Class<?> targetClass) {
		ModelTypeDeffination metaInfo =find((K)targetClass.getSimpleName());
		if(metaInfo!=null) {
			return metaInfo;
		}
		return register(targetClass);
	}

	public List<ModelTypeDeffination> findByType(Class<?> model) {
		List<ModelTypeDeffination> list=new ArrayList<>();
		for(ModelTypeDeffination meta:getCache().values()) {
			if(model.isAssignableFrom(meta.getType())) {
				list.add(meta);
			}
		}
		return list;
	}
	
	public ModelTypeDeffination findById(K id) {
		for(Entry<K, ModelTypeDeffination> entry:getCache().entrySet()) {
			if(entry.getKey().equals(id)) {
				return entry.getValue();
			}
		}
		return getContainer(id);
	}
	
	public ModelPropertyDiffinationGroup getPropertyMetaData(K typeId, String _keyPath) {
		ModelTypeDeffination classMeta=findById(typeId);
		if(classMeta==null) {
			return null;
		}
		return classMeta.getProperty(_keyPath);
	}
	
	@Override
	protected void preregister(K key, ModelTypeDeffination value) {
		LoggerConsole.screen("Model Deffination", "Registering for deffination with id : "+value);
	}
	
	@Override
	protected void postregister(K key, ModelTypeDeffination value) {
		LoggerConsole.screen("Model Deffination", "Registered for deffination with id : "+key);
	}
}
