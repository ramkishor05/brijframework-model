package org.brijframework.model.factories.metadata;

import java.util.List;

import org.brijframework.model.factories.ModelMetaDataFactory;
import org.brijframework.model.metadata.TypeModelMetaData;

public interface TypeModelMetaDataFactory<K,T extends TypeModelMetaData>  extends ModelMetaDataFactory<K, TypeModelMetaData>{

	List<T> findByType(Class<?> cls);
}
