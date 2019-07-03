package org.brijframework.model.factories.json;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.brijframework.model.config.ModelConstants;
import org.brijframework.model.config.asm.ResourcesModelConfig;
import org.brijframework.model.factories.asm.ClassMetaSetupFactoryImpl;
import org.brijframework.model.setup.impl.ModelMetaSetup;
import org.brijframework.model.setup.impl.PropertyMetaSetup;
import org.brijframework.resources.factory.json.JsonResourceFactory;
import org.brijframework.resources.files.json.JsonResource;
import org.brijframework.support.config.Assignable;
import org.brijframework.util.accessor.PropertyAccessorUtil;
import org.brijframework.util.asserts.Assertion;
import org.brijframework.util.reflect.InstanceUtil;
import org.brijframework.util.support.Access;
import org.json.JSONException;


public class JsonMetaSetupFactory extends ClassMetaSetupFactoryImpl{
	
	public JsonMetaSetupFactory() {
	}
	
	private static JsonMetaSetupFactory factory;

	@Assignable
	public static JsonMetaSetupFactory getFactory() {
		if (factory == null) {
			factory = new JsonMetaSetupFactory();
		}
		return factory;
	}
	
	@SuppressWarnings("unchecked")
	public List<ResourcesModelConfig> configration() {
		Object resources=getContainer().getContext().getProperties().get(ModelConstants.APPLICATION_BOOTSTRAP_CONFIG_MODEL_JSON_LOCATION);
		if (resources==null) {
			System.err.println("Model configration not found :"+ModelConstants.APPLICATION_BOOTSTRAP_CONFIG_MODEL_JSON_LOCATION);
			return null;
		}
		System.err.println("Model configration found :"+ModelConstants.APPLICATION_BOOTSTRAP_CONFIG_MODEL_JSON_LOCATION+" | "+resources);
		if(resources instanceof List) {
			return build((List<Map<String, Object>>)resources);
		}else if(resources instanceof Map) {
			return build((Map<String, Object>)resources);
		}else {
			System.err.println("Invalid model configration : "+resources);
			return null;
		}
	}
	
	private List<ResourcesModelConfig> build(Map<String, Object> resource) {
		List<ResourcesModelConfig> configs=new ArrayList<ResourcesModelConfig>();
		configs.add(InstanceUtil.getInstance(ResourcesModelConfig.class, resource));
		return configs;
	}

	private List<ResourcesModelConfig> build(List<Map<String, Object>> resources) {
		List<ResourcesModelConfig> configs=new ArrayList<ResourcesModelConfig>();
		for(Map<String, Object> resource:resources) {
			configs.add(InstanceUtil.getInstance(ResourcesModelConfig.class, resource));
		}
		return configs;
	}
	
	@Override
	public JsonMetaSetupFactory loadFactory() {
		List<ResourcesModelConfig> configs=configration();
		if(configs==null) {
			System.err.println("Invalid model configration : "+configs);
			return this;
		}
		for(ResourcesModelConfig modelConfig:configs) {
			System.out.println(modelConfig.getLocation());
			if(!modelConfig.isEnable()) {
				System.err.println("Model configration disabled found :"+modelConfig.getLocation());
				continue;
			}
			Collection<JsonResource> resources=JsonResourceFactory.factory().getResources(modelConfig.getLocation());
			System.out.println(resources);
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
		ModelMetaSetup metaSetup=InstanceUtil.getInstance(ModelMetaSetup.class);
		@SuppressWarnings("unchecked")
		Map<String,Map<String,Object>> properties=(Map<String, Map<String, Object>>) resourceMap.remove("properties");
		if(properties!=null) {
			properties.forEach((key,value)->{
				metaSetup.getProperties().put(key, InstanceUtil.getInstance(PropertyMetaSetup.class,value));
			});
		}
		PropertyAccessorUtil.setProperties(metaSetup, resourceMap,Access.PRIVATE);
		this.register(metaSetup);
	}
}
