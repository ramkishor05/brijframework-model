package org.brijframework.model.container.asm;

import org.brijframework.container.impl.module.AbstractModuleContainer;
import org.brijframework.group.Group;
import org.brijframework.model.container.ModelContainer;
import org.brijframework.model.factories.deffination.TypeModelDiffinationFactory;
import org.brijframework.model.group.ModelMetaDataGroup;
import org.brijframework.support.factories.SingletonFactory;
import org.brijframework.support.ordering.DepandOn;
import org.brijframework.util.factories.ReflectionFactory;
import org.brijframework.util.reflect.InstanceUtil;

@DepandOn(depand=ModelResourceContainer.class)
public class ModelDiffinationContainer extends AbstractModuleContainer implements ModelContainer{

	private static ModelDiffinationContainer container;

	@SingletonFactory
	public static ModelDiffinationContainer getContainer() {
		if (container == null) {
			container = InstanceUtil.getSingletonInstance(ModelDiffinationContainer.class);
		}
		return container;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void init() {
		try {
			ReflectionFactory.getFactory().getExternalClassList().forEach(cls -> {
				if (TypeModelDiffinationFactory.class.isAssignableFrom(cls) && InstanceUtil.isAssignable(cls)) {
					register((Class<? extends TypeModelDiffinationFactory<?,?>>) cls);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			ReflectionFactory.getFactory().getInternalClassList().forEach(cls -> {
				if (TypeModelDiffinationFactory.class.isAssignableFrom(cls) && InstanceUtil.isAssignable(cls)) {
					register((Class<? extends TypeModelDiffinationFactory<?,?>>) cls);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Group load(Object groupKey) {
		Group group = getCache().get(groupKey);
		if (group == null) {
			group = new ModelMetaDataGroup(groupKey);
			getCache().put(groupKey, group);
		}
		return group;
	}
}
