package org.brijframework.model.factories.metadata;

import java.util.List;

import org.brijframework.model.factories.MetaFactory;
import org.brijframework.model.info.ClassModelMetaData;

public interface ClassMetaDataFactory<K,T extends ClassModelMetaData>  extends MetaFactory<String, ClassModelMetaData>{

	List<T> findByType(Class<?> cls);

	List<T> findByTypeWithParent(Class<?> target, String parentID);

}
