package org.brijframework.model.context;

import java.util.List;

import org.brijframework.context.module.ModuleContext;
import org.brijframework.model.ModelDiffination;
import org.brijframework.model.ModelResource;

public interface ModelContext extends ModuleContext{
	
	public List<String> getModelResourceNames();
	
	public List<String> getModelResourceNames(String model);
	
	public ModelResource<?> getModelResource(String name);
	
	public List<? extends ModelResource<?>> getModelResourceList();
	
	public List<? extends ModelResource<?>> getModelResourceList(String model);
	
	public List<?> getModelMetaDataNames();
	
	public List<?> getModelMetaDataNames(String model);
	
	public ModelDiffination<?> getModelMetaData(String name);
	
	public List<? extends ModelDiffination<?>> getModelMetaDataList();
	
	public List<? extends ModelDiffination<?>> getModelMetaDataList(String model);

	public List<? extends ModelDiffination<?>> getModelMetaDataList(Class<?> metaClass);
}
