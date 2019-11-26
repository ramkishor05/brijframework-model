package org.brijframework.model.factories.resource;

import java.util.List;

import org.brijframework.model.factories.MetaFactory;
import org.brijframework.model.resource.ClassModelResource;

public interface ClassModelResourceFactory<K,T extends ClassModelResource> extends MetaFactory<K,ClassModelResource>{

	List<T> findByType(Class<?> cls);

	List<T> findByTypeWithParent(Class<?> target, String parentID);

}
