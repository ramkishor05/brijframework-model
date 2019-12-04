package org.brijframework.model.factories.resource;

import java.util.List;

import org.brijframework.model.factories.ModelResourceFactory;
import org.brijframework.model.resource.TypeModelResource;

public interface TypeModelResourceFactory<K,T extends TypeModelResource> extends ModelResourceFactory<K,TypeModelResource>{

	List<TypeModelResource> findByType(String cls);

}
