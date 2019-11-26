package org.brijframework.model.factories.resource.asm;

import java.util.List;

import org.brijframework.model.factories.resource.ClassModelResourceFactory;
import org.brijframework.model.resource.ClassModelResource;

public abstract class AbstractClassModelResourceFactory extends AbstractModelResourceFactory<String, ClassModelResource> implements ClassModelResourceFactory<String, ClassModelResource>{
	

	@Override
	public List<ClassModelResource> findByType(Class<?> cls) {
		return null;
	}

	@Override
	public List<ClassModelResource> findByTypeWithParent(Class<?> target, String parentID) {
		return null;
	}
	
}