package org.brijframework.model.factories.resource.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.brijframework.model.config.asm.ModelConfigration;
import org.brijframework.model.constants.ModelConstants;
import org.brijframework.model.factories.resource.asm.AbstractTypeModelResourceFactory;
import org.brijframework.model.resource.TypeModelResource;
import org.brijframework.model.resource.impl.PropertyModelResourceImpl;
import org.brijframework.model.resource.impl.RelationPropertyModelResourceImpl;
import org.brijframework.model.resource.impl.TypeModelResourceImpl;
import org.brijframework.resources.factory.json.JsonResourceFactory;
import org.brijframework.resources.files.json.JsonResource;
import org.brijframework.support.factories.SingletonFactory;
import org.brijframework.support.ordering.OrderOn;
import org.brijframework.util.accessor.PropertyAccessorUtil;
import org.brijframework.util.asserts.Assertion;
import org.brijframework.util.factories.ReflectionFactory;
import org.brijframework.util.printer.LoggerConsole;
import org.brijframework.util.reflect.ClassUtil;
import org.brijframework.util.reflect.FieldUtil;
import org.brijframework.util.reflect.InstanceUtil;
import org.brijframework.util.support.Access;
import org.brijframework.util.text.StringUtil;
import org.json.JSONException;

@OrderOn(2)
public class JsonTypeModelResourceFactory extends AbstractTypeModelResourceFactory<String, TypeModelResource>{
	
	public JsonTypeModelResourceFactory() {
	}
	
	private static JsonTypeModelResourceFactory factory;

	@SingletonFactory
	public static JsonTypeModelResourceFactory getFactory() {
		if (factory == null) {
			factory = new JsonTypeModelResourceFactory();
		}
		return factory;
	}
	
	@SuppressWarnings("unchecked")
	public List<ModelConfigration> configration() {
		Object resources=getEnvProperty(ModelConstants.APPLICATION_MODEL_CONFIG);
		if (resources==null) {
			LoggerConsole.screen("ModelConfigration", "Model configration not found :"+ModelConstants.APPLICATION_MODEL_CONFIG);
			return null;
		}
		LoggerConsole.screen("ModelConfigration", "Model configration found :"+ModelConstants.APPLICATION_MODEL_CONFIG+" | "+resources);
		if(resources instanceof List) {
			return build((List<Map<String, Object>>)resources);
		}else if(resources instanceof Map) {
			return build((Map<String, Object>)resources);
		}else {
			LoggerConsole.screen("ModelConfigration","Invalid model configration : "+resources);
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
	public JsonTypeModelResourceFactory loadFactory() {
		List<ModelConfigration> configs=configration();
		if(configs==null) {
			LoggerConsole.screen("ModelConfigration","Invalid model configration : "+configs);
			return this;
		}
		for(ModelConfigration modelConfig:configs) {
			if(!modelConfig.isEnable()) {
				LoggerConsole.screen("ModelConfigration","Model configration disabled found :"+modelConfig.getLocation());
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

	@SuppressWarnings("unchecked")
	public void register(Map<String, Object> resourceMap) {
		TypeModelResourceImpl metaSetup=InstanceUtil.getInstance(TypeModelResourceImpl.class);
		String id=(String) resourceMap.remove("id");
		Assertion.notEmpty(id, "Invalid id for TypeModelResource");
		metaSetup.setId(id);
		String type=(String) resourceMap.remove("type");
		Assertion.notEmpty(type, "Invalid type for TypeModelResource");
		metaSetup.setType(type);
		Class<?> typeClass=ClassUtil.getClass(type);
		Assertion.notEmpty(typeClass, "Not found type for TypeModelResource");
		String name=(String) resourceMap.remove("name");
		if(StringUtil.isEmpty(name)) {
			name=typeClass.getSimpleName();
		}
		metaSetup.setName(name);
		String access=(String) resourceMap.get("access");
		if(StringUtil.isEmpty(access)) {
			access=Access.PUBLIC.toString();
		}
		metaSetup.setAccess(access);
		Map<String, Field> fieldMap = FieldUtil.getAllFieldMap(typeClass, Access.PRIVATE);
		Map<String,Map<String,Object>> properties=(Map<String, Map<String, Object>>) resourceMap.remove("properties");
		if(properties!=null) {
			properties.forEach((key,value)->{
				Field field = fieldMap.get(key);
				Assertion.notEmpty(field, "Property not found for "+type+" of "+id);
				if(!ReflectionFactory.getFactory().isProjectClass(field.getType())) {
					 metaSetup.getProperties().put(key, InstanceUtil.getInstance(PropertyModelResourceImpl.class,value));
				}else {
				     metaSetup.getProperties().put(key, InstanceUtil.getInstance(RelationPropertyModelResourceImpl.class,value));
				}
			});
		}
		PropertyAccessorUtil.setProperties(metaSetup, resourceMap, Access.PRIVATE);
		this.register(metaSetup.getId(), metaSetup);
	}
	
}
