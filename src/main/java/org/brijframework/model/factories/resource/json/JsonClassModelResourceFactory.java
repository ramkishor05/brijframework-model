package org.brijframework.model.factories.resource.json;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.brijframework.model.config.ModelConstants;
import org.brijframework.model.config.asm.ModelConfigration;
import org.brijframework.model.factories.resource.asm.AbstractClassModelResourceFactory;
import org.brijframework.model.resource.ClassModelResource;
import org.brijframework.model.setup.impl.ClassModelResourceObject;
import org.brijframework.model.setup.impl.PropertyModelResourceObject;
import org.brijframework.resources.factory.json.JsonResourceFactory;
import org.brijframework.resources.files.json.JsonResource;
import org.brijframework.support.config.OrderOn;
import org.brijframework.support.config.SingletonFactory;
import org.brijframework.util.accessor.PropertyAccessorUtil;
import org.brijframework.util.asserts.Assertion;
import org.brijframework.util.printer.ConsolePrint;
import org.brijframework.util.reflect.InstanceUtil;
import org.brijframework.util.support.Access;
import org.json.JSONException;

@OrderOn(1)
public class JsonClassModelResourceFactory extends AbstractClassModelResourceFactory{
	
	public JsonClassModelResourceFactory() {
	}
	
	private static JsonClassModelResourceFactory factory;

	@SingletonFactory
	public static JsonClassModelResourceFactory getFactory() {
		if (factory == null) {
			factory = new JsonClassModelResourceFactory();
		}
		return factory;
	}
	
	@SuppressWarnings("unchecked")
	public List<ModelConfigration> configration() {
		Object resources=getEnvProperty(ModelConstants.APPLICATION_MODEL_CONFIG);
		if (resources==null) {
			ConsolePrint.screen("ModelConfigration", "Model configration not found :"+ModelConstants.APPLICATION_MODEL_CONFIG);
			return null;
		}
		ConsolePrint.screen("ModelConfigration", "Model configration found :"+ModelConstants.APPLICATION_MODEL_CONFIG+" | "+resources);
		if(resources instanceof List) {
			return build((List<Map<String, Object>>)resources);
		}else if(resources instanceof Map) {
			return build((Map<String, Object>)resources);
		}else {
			ConsolePrint.screen("ModelConfigration","Invalid model configration : "+resources);
			return null;
		}
	}
	
	private List<ModelConfigration> build(Map<String, Object> resource) {
		List<ModelConfigration> configs=new ArrayList<ModelConfigration>();
		configs.add(InstanceUtil.getInstance(ModelConfigration.class, resource));
		return configs;
	}

	private List<ModelConfigration> build(List<Map<String, Object>> resources) {
		List<ModelConfigration> configs=new ArrayList<ModelConfigration>();
		for(Map<String, Object> resource:resources) {
			configs.add(InstanceUtil.getInstance(ModelConfigration.class, resource));
		}
		return configs;
	}
	
	@Override
	public JsonClassModelResourceFactory loadFactory() {
		List<ModelConfigration> configs=configration();
		if(configs==null) {
			ConsolePrint.screen("ModelConfigration","Invalid model configration : "+configs);
			return this;
		}
		for(ModelConfigration modelConfig:configs) {
			if(!modelConfig.isEnable()) {
				ConsolePrint.screen("ModelConfigration","Model configration disabled found :"+modelConfig.getLocation());
				continue;
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
		ClassModelResource metaSetup=(ClassModelResource) InstanceUtil.getInstance(ClassModelResourceObject.class);
		@SuppressWarnings("unchecked")
		Map<String,Map<String,Object>> properties=(Map<String, Map<String, Object>>) resourceMap.remove("properties");
		if(properties!=null) {
			properties.forEach((key,value)->{
				metaSetup.getProperties().put(key, InstanceUtil.getInstance(PropertyModelResourceObject.class,value));
			});
		}
		PropertyAccessorUtil.setProperties(metaSetup, resourceMap,Access.PRIVATE);
		this.register(metaSetup);
	}

	
}
