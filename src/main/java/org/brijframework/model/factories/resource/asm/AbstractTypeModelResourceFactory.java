package org.brijframework.model.factories.resource.asm;

import java.util.List;

import org.brijframework.model.factories.asm.AbstractModelResourceFactory;
import org.brijframework.model.factories.resource.TypeModelResourceFactory;
import org.brijframework.model.resource.TypeModelResource;

public abstract class AbstractTypeModelResourceFactory<K, T extends TypeModelResource>  extends AbstractModelResourceFactory<K, TypeModelResource> implements TypeModelResourceFactory<K, TypeModelResource>{

	@Override
	public List<TypeModelResource> findByType(String cls) {
		
		return null;
	}
	
}