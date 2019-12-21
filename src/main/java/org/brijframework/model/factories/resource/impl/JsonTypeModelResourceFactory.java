package org.brijframework.model.factories.resource.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.brijframework.Access;
import org.brijframework.model.config.asm.ModelConfigration;
import org.brijframework.model.constants.ModelConstants;
import org.brijframework.model.factories.resource.asm.AbstractTypeModelResourceFactory;
import org.brijframework.model.resource.ConstructorModelResource;
import org.brijframework.model.resource.ParameterModelResource;
import org.brijframework.model.resource.TypeModelResource;
import org.brijframework.model.resource.impl.ConstructorModelResourceImpl;
import org.brijframework.model.resource.impl.ParameterModelResourceImpl;
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
import org.brijframework.util.support.Constants;
import org.brijframework.util.support.ReflectionAccess;
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
		TypeModelResourceImpl typeModelResource=InstanceUtil.getInstance(TypeModelResourceImpl.class);
		String id=(String) resourceMap.remove("id");
		Assertion.notEmpty(id, "Invalid id for TypeModelResource");
		typeModelResource.setId(id);
		String type=(String) resourceMap.remove("type");
		Assertion.notEmpty(type, "Invalid type for TypeModelResource");
		typeModelResource.setType(type);
		Class<?> typeClass=ClassUtil.getClass(type);
		Assertion.notEmpty(typeClass, "Not found type for TypeModelResource");
		String name=(String) resourceMap.remove("name");
		if(StringUtil.isEmpty(name)) {
			name=typeClass.getSimpleName();
		}
		typeModelResource.setName(name);
		String access=(String) resourceMap.get("access");
		if(StringUtil.isEmpty(access)) {
			access=ReflectionAccess.PUBLIC.toString();
		}
		typeModelResource.setAccess(access);
		Object constructor = resourceMap.get("constructor");
		if(constructor instanceof Map) {
			typeModelResource.setConstructor(createConstructor(typeModelResource, (Map<String,Object>) resourceMap.get("constructor")));
		}else {
			typeModelResource.setConstructor(createConstructor(typeModelResource,null));
		}
		Map<String, Field> fieldMap = FieldUtil.getAllFieldMap(typeClass, ReflectionAccess.PRIVATE);
		Map<String,Map<String,Object>> properties=(Map<String, Map<String, Object>>) resourceMap.remove("properties");
		if(properties!=null) {
			properties.forEach((key,value)->{
				Field field = fieldMap.get(key);
				Assertion.notEmpty(field, "Property not found for "+type+" of "+id);
				if(!ReflectionFactory.getFactory().isProjectClass(field.getType())) {
					 typeModelResource.getProperties().put(key, InstanceUtil.getInstance(PropertyModelResourceImpl.class,value));
				}else {
				     typeModelResource.getProperties().put(key, InstanceUtil.getInstance(RelationPropertyModelResourceImpl.class,value));
				}
			});
		}
		PropertyAccessorUtil.setProperties(typeModelResource, resourceMap, ReflectionAccess.PRIVATE);
		this.register(typeModelResource.getId(), typeModelResource);
	}
	
	@SuppressWarnings("unchecked")
	private ConstructorModelResource<?> createConstructor(TypeModelResource typeModelResource,Map<String,Object> constructor) {
		ConstructorModelResourceImpl constructorModelResource = new ConstructorModelResourceImpl();
		if(constructor!=null) {
			String access=(String) constructor.get("access");
			constructorModelResource.setAccess(access);
			String id=(String) constructor.get("id");
			constructorModelResource.setId(StringUtil.isEmpty(id)|| Constants.DEFAULT.equalsIgnoreCase(id)? typeModelResource.getId(): id);
			String name=(String) constructor.get("name");
			constructorModelResource.setName(StringUtil.isEmpty(name)|| Constants.DEFAULT.equalsIgnoreCase(name)? typeModelResource.getName(): name);
			List<Map<String,Object>> parameters=(List<Map<String, Object>>) constructor.get("parameters");
			if(parameters!=null)
			for(Map<String,Object> modelParam: parameters) {
				constructorModelResource.getParameterList().add(getModelParam(modelParam));
			}
		}
		return constructorModelResource;
	}

	private ParameterModelResource getModelParam(Map<String, Object> modelParam) {
		ParameterModelResourceImpl parameterModelResource=new ParameterModelResourceImpl();
		parameterModelResource.setId((String) modelParam.get("name"));
		Integer index=null;
		try {
		   index=Integer.valueOf((String.valueOf(modelParam.get("index"))));
		}catch (NumberFormatException e) {
		}
		Assertion.notNull(index, "Parameter index is required.");
		parameterModelResource.setIndex(index);
		String type=(String) modelParam.get("type");
		Assertion.notNull(type, "Parameter type is required.");
		parameterModelResource.setType(type);
		parameterModelResource.setAccess(Access.AUTO.toString());
		return parameterModelResource;
	}

}
