package org.brijframework.model.context.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.brijframework.model.ModelMetaData;
import org.brijframework.model.ModelResource;
import org.brijframework.model.context.asm.AbstractModelContext;
import org.brijframework.model.factories.metadata.impl.TypeModelMetaDataFactoryImpl;
import org.brijframework.model.factories.resource.impl.TypeModelResourceFactoryImpl;
import org.brijframework.model.metadata.TypeModelMetaData;
import org.brijframework.model.resource.TypeModelResource;

public class ModelContextImpl extends AbstractModelContext{

	@Override
	public List<String> getModelResourceNames() {
		List<String> list=new ArrayList<String>();
		for(Entry<String, TypeModelResource> entry:TypeModelResourceFactoryImpl.getFactory().getCache().entrySet()) {
			list.add(entry.getKey());
		}
		return list;
	}

	@Override
	public List<String> getModelResourceNames(String model) {
		List<String> list=new ArrayList<String>();
		for(Entry<String, TypeModelResource> entry:TypeModelResourceFactoryImpl.getFactory().getCache().entrySet()) {
			TypeModelResource modelResource = entry.getValue();
			if(modelResource!=null && modelResource.getType()!=null && modelResource.getType().equals(model)) {
				list.add(entry.getKey());
			}
		}
		return list;
	}

	@Override
	public ModelResource<?> getModelResource(String key) {
		return TypeModelResourceFactoryImpl.getFactory().find(key);
	}

	@Override
	public List<? extends ModelResource<?>> getModelResourceList() {
		List<TypeModelResource> list=new ArrayList<TypeModelResource>();
		for(Entry<String, TypeModelResource> entry:TypeModelResourceFactoryImpl.getFactory().getCache().entrySet()) {
			TypeModelResource modelResource = entry.getValue();
			if(modelResource!=null) {
				list.add(modelResource);
			}
		}
		return list;
	}

	@Override
	public List<? extends ModelResource<?>> getModelResourceList(String model) {
		List<TypeModelResource> list=new ArrayList<TypeModelResource>();
		for(Entry<String, TypeModelResource> entry:TypeModelResourceFactoryImpl.getFactory().getCache().entrySet()) {
			TypeModelResource modelResource = entry.getValue();
			if(modelResource!=null && modelResource.getType()!=null && modelResource.getType().equals(model)) {
				list.add(modelResource);
			}
		}
		return list;
	}

	@Override
	public List<?> getModelMetaDataNames() {
		List<String> list=new ArrayList<String>();
		for(Entry<String, TypeModelMetaData> entry:TypeModelMetaDataFactoryImpl.getFactory().getCache().entrySet()) {
			list.add(entry.getKey());
		}
		return list;
	}

	@Override
	public List<?> getModelMetaDataNames(String model) {
		List<String> list=new ArrayList<String>();
		for(Entry<String, TypeModelMetaData> entry:TypeModelMetaDataFactoryImpl.getFactory().getCache().entrySet()) {
			TypeModelMetaData modelMetaData = entry.getValue();
			if(modelMetaData!=null &&modelMetaData.getOwner()!=null && modelMetaData.getOwner().getId().equals(model)) {
				list.add(entry.getKey());
			}
		}
		return list;
	}

	@Override
	public ModelMetaData<?> getModelMetaData(String key) {
		return TypeModelMetaDataFactoryImpl.getFactory().find(key);
	}

	@Override
	public List<? extends ModelMetaData<?>> getModelMetaDataList() {
		List<ModelMetaData<?>> list=new ArrayList<ModelMetaData<?>>();
		for(Entry<String, TypeModelMetaData> entry:TypeModelMetaDataFactoryImpl.getFactory().getCache().entrySet()) {
			TypeModelMetaData typeModelMetaData = entry.getValue();
			if(typeModelMetaData!=null) {
				list.add(typeModelMetaData);
			}
		}
		return list;
	}

	@Override
	public List<? extends ModelMetaData<?>> getModelMetaDataList(String model) {
		List<ModelMetaData<?>> list=new ArrayList<ModelMetaData<?>>();
		for(Entry<String, TypeModelMetaData> entry:TypeModelMetaDataFactoryImpl.getFactory().getCache().entrySet()) {
			TypeModelMetaData typeModelMetaData = entry.getValue();
			if(typeModelMetaData!=null && typeModelMetaData.getOwner()!=null && typeModelMetaData.getOwner().getId().equals(model)) {
				list.add(typeModelMetaData);
			}
		}
		return list;
	}

	@Override
	public List<? extends ModelMetaData<?>> getModelMetaDataList(Class<?> metaClass) {
		List<ModelMetaData<?>> list=new ArrayList<ModelMetaData<?>>();
		for(Entry<String, TypeModelMetaData> entry:TypeModelMetaDataFactoryImpl.getFactory().getCache().entrySet()) {
			TypeModelMetaData typeModelMetaData = entry.getValue();
			if(typeModelMetaData!=null && typeModelMetaData.getType()!=null && metaClass.isAssignableFrom(typeModelMetaData.getType())) {
				list.add(typeModelMetaData);
			}
		}
		return list;
	}
	
}
