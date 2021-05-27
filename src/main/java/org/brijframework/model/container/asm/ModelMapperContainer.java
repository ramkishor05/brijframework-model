package org.brijframework.model.container.asm;

import org.brijframework.container.impl.module.AbstractModuleContainer;
import org.brijframework.group.Group;
import org.brijframework.model.group.ModelMetaDataGroup;
import org.brijframework.model.mapper.factories.ModelMapperFactory;
import org.brijframework.support.factories.SingletonFactory;
import org.brijframework.support.ordering.DepandOn;
import org.brijframework.util.factories.ReflectionFactory;
import org.brijframework.util.reflect.InstanceUtil;

@DepandOn(depand=ModelDiffinationContainer.class)
public class ModelMapperContainer extends AbstractModuleContainer{

	private static ModelMapperContainer container;

	@SingletonFactory
	public static ModelMapperContainer getContainer() {
		if (container == null) {
			container = new ModelMapperContainer();
		}
		return container;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void init() {
		try {
			ReflectionFactory.getFactory().getExternalClassList().forEach(cls -> {
				if (ModelMapperFactory.class.isAssignableFrom(cls) && InstanceUtil.isAssignable(cls)) {
					register((Class<? extends ModelMapperFactory<?,?>>) cls);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			ReflectionFactory.getFactory().getInternalClassList().forEach(cls -> {
				if (ModelMapperFactory.class.isAssignableFrom(cls) && InstanceUtil.isAssignable(cls)) {
					register((Class<? extends ModelMapperFactory<?,?>>) cls);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Group load(Object groupKey) {
		Group group=get(groupKey);
		if(group==null) {
			group = new ModelMetaDataGroup(groupKey);
			getCache().put(groupKey, group);
		}
		return group;
	}

}
