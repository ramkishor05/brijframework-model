package org.brijframework.model.factories.deffination;

import java.util.List;

import org.brijframework.model.diffination.ModelTypeDeffination;
import org.brijframework.model.factories.ModelDiffinationFactory;

public interface TypeModelDiffinationFactory<K,T extends ModelTypeDeffination>  extends ModelDiffinationFactory<K, ModelTypeDeffination>{

	List<T> findByType(Class<?> cls);
}
