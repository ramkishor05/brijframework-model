package org.brijframework.model.container.asm;

import org.brijframework.asm.container.AbstractContainer;
import org.brijframework.group.Group;
import org.brijframework.model.container.ModelContainer;
import org.brijframework.model.factories.ClassMetaSetupFactory;
import org.brijframework.model.group.MetaInfoGroup;
import org.brijframework.support.config.Assignable;
import org.brijframework.util.reflect.InstanceUtil;
import org.brijframework.util.reflect.ReflectionUtils;

public class MetaSetupContainer extends AbstractContainer implements ModelContainer {

	private static MetaSetupContainer container;

	@Assignable
	public static MetaSetupContainer getContainer() {
		if (container == null) {
			container = InstanceUtil.getSingletonInstance(MetaSetupContainer.class);
		}
		return container;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void init() {
		try {
			ReflectionUtils.getClassListFromExternal().forEach(cls -> {
				if (ClassMetaSetupFactory.class.isAssignableFrom(cls) && InstanceUtil.isAssignable(cls)) {
					register((Class<? extends ClassMetaSetupFactory>) cls);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			ReflectionUtils.getClassListFromInternal().forEach(cls -> {
				if (ClassMetaSetupFactory.class.isAssignableFrom(cls) && InstanceUtil.isAssignable(cls)) {
					register((Class<? extends ClassMetaSetupFactory>) cls);
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
			group = new MetaInfoGroup(groupKey);
			getCache().put(groupKey, group);
		}
		return group;
	}
}
