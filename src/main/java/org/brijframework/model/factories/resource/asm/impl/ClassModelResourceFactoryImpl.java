package org.brijframework.model.factories.resource.asm.impl;

import java.util.List;
import java.util.Map.Entry;

import org.brijframework.model.factories.resource.ClassModelResourceFactory;
import org.brijframework.model.factories.resource.asm.ModelResourceFactoryImpl;
import org.brijframework.model.resource.ClassModelResource;
import org.brijframework.support.config.Assignable;

public class ClassModelResourceFactoryImpl extends ModelResourceFactoryImpl<String,ClassModelResource> implements ClassModelResourceFactory<String,ClassModelResource>{
	
	protected ClassModelResourceFactoryImpl() {
	}

	protected static ClassModelResourceFactoryImpl factory;

	@Assignable
	public static ClassModelResourceFactoryImpl getFactory() {
		if (factory == null) {
			factory = new ClassModelResourceFactoryImpl();
		}
		return factory;
	}

	@Override
	public ClassModelResourceFactoryImpl clear() {
		if (getCache() != null) {
			getCache().clear();
		}
		return this;
	}

	public ClassModelResource getClassInfo(String id) {
		for(Entry<String, ClassModelResource> entry:getCache().entrySet()) {
			if(entry.getKey().equals(id)) {
				return entry.getValue();
			}
		}
		return getContainer(id);
	}

	@Override
	public ClassModelResourceFactoryImpl loadFactory() {
		return this;
	}

	@Override
	public List<ClassModelResource> getClassSetupList(Class<?> clsName) {
		return null;
	}

	@Override
	public List<ClassModelResource> getClassSetupList(Class<?> target, String parentID) {
		return null;
	}

	@Override
	public ClassModelResource getClassSetup(String id) {
		return null;
	}

	@Override
	protected void preregister(String key, ClassModelResource value) {
		
	}

	@Override
	protected void postregister(String key, ClassModelResource value) {
		
	}

}