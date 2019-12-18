package org.brijframework.model.mapper.factories.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.brijframework.factories.impl.module.AbstractModuleFactory;
import org.brijframework.model.config.ModelConfig;
import org.brijframework.model.config.asm.ModelConfigration;
import org.brijframework.model.constants.ModelConstants;
import org.brijframework.model.mapper.factories.ModelMapperFactory;
import org.brijframework.model.mapper.model.PropertyModelMapperResource;
import org.brijframework.model.mapper.model.TypeModelMapperResource;
import org.brijframework.resources.factory.json.JsonResourceFactory;
import org.brijframework.resources.files.json.JsonResource;
import org.brijframework.support.factories.SingletonFactory;
import org.brijframework.util.asserts.Assertion;
import org.brijframework.util.reflect.FieldUtil;
import org.brijframework.util.reflect.InstanceUtil;
import org.brijframework.util.support.ReflectionAccess;
import org.json.JSONException;

public class JsonTypeModelMapperResourceFactory extends AbstractModuleFactory<String,TypeModelMapperResource> implements ModelMapperFactory<String,TypeModelMapperResource> {

	protected JsonTypeModelMapperResourceFactory() {
	}

	protected static JsonTypeModelMapperResourceFactory factory;

	@SingletonFactory
	public static JsonTypeModelMapperResourceFactory getFactory() {
		if (factory == null) {
			factory = new JsonTypeModelMapperResourceFactory();
		}
		return factory;
	}


	@SuppressWarnings("unchecked")
	public List<ModelConfig> configration() {
		Object resources=getContainer().getContext().getEnvironment().get(ModelConstants.APPLICATION_MODEL_CONFIG_MAPPER);
		if (resources==null) {
			System.err.println("Mapper configration not found :"+ModelConstants.APPLICATION_MODEL_CONFIG_MAPPER);
			return null;
		}
		System.err.println("Mapper configration found :"+ModelConstants.APPLICATION_MODEL_CONFIG_MAPPER);
		if(resources instanceof List) {
			return build((List<Map<String, Object>>)resources);
		}else if(resources instanceof Map) {
			return build((Map<String, Object>)resources);
		}else {
			System.err.println("Invalid mapper configration : "+resources);
			return null;
		}
	}
	
	private List<ModelConfig> build(Map<String, Object> resource) {
		List<ModelConfig> configs=new ArrayList<ModelConfig>();
		configs.add(InstanceUtil.getInstance(ModelConfigration.class, resource));
		return configs;
	}

	private List<ModelConfig> build(List<Map<String, Object>> resources) {
		List<ModelConfig> configs=new ArrayList<ModelConfig>();
		for(Map<String, Object> resource:resources) {
			configs.add(InstanceUtil.getInstance(ModelConfigration.class, resource));
		}
		return configs;
	}
	
	
	@Override
	public JsonTypeModelMapperResourceFactory loadFactory() {
		List<ModelConfig> configs=configration();
		if(configs==null) {
			System.err.println("Invalid mapper configration : "+configs);
			return this;
		}
		for(ModelConfig modelConfig:configs) {
			if(!modelConfig.isEnable()) {
				System.err.println("mapper configration disabled found :"+modelConfig.getLocation());
			
			}
			Collection<JsonResource> resources=JsonResourceFactory.factory().getResources(modelConfig.getLocation());
			for(JsonResource resource:resources) {
				if(resource.isJsonList()) {
				   try {
					  register(resource.toObjectList());
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
				if(resource.isJsonObject()) {
					try {
						register(resource.toObjectMap());
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return this;
	}
	@SuppressWarnings("unchecked")
	public void register(List<Object> resources) {
		Assertion.notNull(resources, "Invalid resources");
		for(Object object:resources) {
			if(object instanceof Map) {
				register((Map<String, Object>)object);
			}
		}
	}

	public void register(Map<String, Object> resourceMap) {
		Assertion.notNull(resourceMap, "Invalid target :"+resourceMap);
		@SuppressWarnings("unchecked")
		Map<String,Map<String,Object>> properties=(Map<String, Map<String, Object>>) resourceMap.remove("properties");
		TypeModelMapperResource metaSetup=InstanceUtil.getInstance(TypeModelMapperResource.class,resourceMap);
		if(properties!=null) {
			properties.forEach((key,property)->{
				PropertyModelMapperResource pptMapperModel=getPropertyMapper(metaSetup.getType(),key,property);
				pptMapperModel.setId(key);
				String destinationKey=metaSetup.getName()+"_"+pptMapperModel.getDestination();
				String sourceKey=metaSetup.getName()+"_"+pptMapperModel.getSource();
				String idKey=metaSetup.getName()+"_"+pptMapperModel.getId();
				System.err.println("PptMapperModel    :"+idKey);
				metaSetup.getProperties().put(idKey,pptMapperModel );
				if(pptMapperModel.getSource()!=null && !metaSetup.getProperties().containsKey(sourceKey)) {
				   System.err.println("PptMapperModel    :"+metaSetup.getName()+"_"+pptMapperModel.getSource());
				   metaSetup.getProperties().put(metaSetup.getName()+"_"+pptMapperModel.getSource(),pptMapperModel );
				}
				if(pptMapperModel.getDestination()!=null && !metaSetup.getProperties().containsKey(destinationKey)) {
				   System.err.println("PptMapperModel    :"+destinationKey);
				   metaSetup.getProperties().put(metaSetup.getName()+"_"+pptMapperModel.getDestination(),pptMapperModel );
				}
			});
		}
		this.register(metaSetup.getId(),metaSetup);
	}


	private PropertyModelMapperResource getPropertyMapper(Class<?> type,String _field, Map<String, Object> property) {
		PropertyModelMapperResource pptMapperModel=InstanceUtil.getInstance(PropertyModelMapperResource.class,property);
		if(type!=null) {
		  pptMapperModel.setTarget(FieldUtil.getField(type, _field, ReflectionAccess.PRIVATE));
		}
		return pptMapperModel;
	}


	@Override
	protected void preregister(String key, TypeModelMapperResource value) {
	}


	@Override
	protected void postregister(String key, TypeModelMapperResource value) {
	}
}
