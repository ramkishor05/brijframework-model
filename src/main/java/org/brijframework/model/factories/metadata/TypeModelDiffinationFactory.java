package org.brijframework.model.factories.metadata;

import java.util.List;

import org.brijframework.model.diffination.TypeModelDiffination;
import org.brijframework.model.factories.ModelDiffinationFactory;

public interface TypeModelDiffinationFactory<K,T extends TypeModelDiffination>  extends ModelDiffinationFactory<K, TypeModelDiffination>{

	List<T> findByType(Class<?> cls);
}
