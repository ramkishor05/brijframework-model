package org.brijframework.model.container.asm;

import org.brijframework.container.impl.module.AbstractModuleContainer;
import org.brijframework.group.Group;
import org.brijframework.model.group.ModelMetaDataGroup;
import org.brijframework.model.mapper.factories.ModelMapperFactory;
import org.brijframework.support.config.DepandOn;
import org.brijframework.support.config.SingletonFactory;
import org.brijframework.util.factories.ReflectionFactory;
import org.brijframework.util.reflect.InstanceUtil;

@DepandOn(depand=ModelDiffinationContainer.class)
public class MapperContainer extends AbstractModuleContainer{

	private static MapperContainer container;

	@SingletonFactory
	public static MapperContainer getContainer() {
		if (container == null) {
			container = InstanceUtil.getSingletonInstance(MapperContainer.class);
		}
		return container;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void init() {
		try {
			ReflectionFactory.getFactory().getClassListFromExternal().forEach(cls -> {
				if (ModelMapperFactory.class.isAssignableFrom(cls) && InstanceUtil.isAssignable(cls)) {
					register((Class<? extends ModelMapperFactory<?,?>>) cls);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			ReflectionFactory.getFactory().getClassListFromInternal().forEach(cls -> {
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
