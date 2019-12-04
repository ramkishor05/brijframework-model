package org.brijframework.model.context;

import java.util.List;

import org.brijframework.context.module.ModuleContext;
import org.brijframework.model.ModelMetaData;
import org.brijframework.model.ModelResource;

public interface ModelContext extends ModuleContext{
	
	public List<String> getModelResourceNames();
	
	public List<String> getModelResourceNames(String model);
	
	public ModelResource<?> getModelResource(String name);
	
	public List<? extends ModelResource<?>> getModelResourceList();
	
	public List<? extends ModelResource<?>> getModelResourceList(String model);
	
	public List<?> getModelMetaDataNames();
	
	public List<?> getModelMetaDataNames(String model);
	
	public ModelMetaData<?> getModelMetaData(String name);
	
	public List<? extends ModelMetaData<?>> getModelMetaDataList();
	
	public List<? extends ModelMetaData<?>> getModelMetaDataList(String model);

	public List<? extends ModelMetaData<?>> getModelMetaDataList(Class<?> metaClass);
}
