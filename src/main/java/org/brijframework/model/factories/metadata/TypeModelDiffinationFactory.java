package org.brijframework.model.factories.metadata;

import java.util.List;

import org.brijframework.model.diffination.ModelTypeDiffination;
import org.brijframework.model.factories.ModelDiffinationFactory;

public interface TypeModelDiffinationFactory<K,T extends ModelTypeDiffination>  extends ModelDiffinationFactory<K, ModelTypeDiffination>{

	List<T> findByType(Class<?> cls);
}
