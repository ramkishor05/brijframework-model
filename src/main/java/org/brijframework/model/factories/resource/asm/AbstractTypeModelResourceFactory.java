package org.brijframework.model.factories.resource.asm;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map.Entry;

import org.brijframework.model.factories.asm.AbstractModelResourceFactory;
import org.brijframework.model.factories.resource.TypeModelResourceFactory;
import org.brijframework.model.resource.PropertyModelResource;
import org.brijframework.model.resource.TypeModelResource;
import org.brijframework.model.resource.impl.PropertyModelResourceImpl;
import org.brijframework.model.resource.impl.TypeModelResourceImpl;
import org.brijframework.util.accessor.MetaAccessorUtil;
import org.brijframework.util.support.Access;

public abstract class AbstractTypeModelResourceFactory<K, T extends TypeModelResource>  extends AbstractModelResourceFactory<K, TypeModelResource> implements TypeModelResourceFactory<K, TypeModelResource>{

	@Override
	public List<TypeModelResource> findByType(String cls) {
		
		return null;
	}
	

	@SuppressWarnings("unchecked")
	public TypeModelResource createOrload(Class<?> typeClass) {
		TypeModelResource typeModelResource = find((K)typeClass.getSimpleName());
		if(typeModelResource!=null) {
			return typeModelResource;
		}
		TypeModelResourceImpl typeModelResourceImpl=new TypeModelResourceImpl();
		typeModelResourceImpl.setAccess(Access.PUBLIC.toString());
		typeModelResourceImpl.setId(typeClass.getSimpleName());
		typeModelResourceImpl.setType(typeClass.getName());
		typeModelResourceImpl.setName(typeClass.getSimpleName());
		for(Entry<String, Method> entry: MetaAccessorUtil.getterPropertyDescriptorList(typeClass).entrySet()) {
			typeModelResourceImpl.getProperties().put(entry.getKey(), getPropertyModelResource(entry.getKey(), typeClass.getSimpleName(), entry.getValue()));
		}
		this.register((K)typeClass.getSimpleName(), typeModelResourceImpl);
		return typeModelResourceImpl;
	}
	
	private PropertyModelResource<?> getPropertyModelResource(String id,String model, Method method) {
		PropertyModelResourceImpl propertyModelResource=new PropertyModelResourceImpl();
		propertyModelResource.setId(id);
		propertyModelResource.setAccess(Access.PUBLIC.toString());
		propertyModelResource.setModel(model);
		propertyModelResource.setType(method.getReturnType().getName());
		propertyModelResource.setRequired(false);
		return propertyModelResource;
	}
	
	
}