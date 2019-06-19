package org.brijframework.meta.container.asm;

import org.brijframework.asm.container.AbstractContainer;
import org.brijframework.group.Group;
import org.brijframework.meta.container.MetaContainer;
import org.brijframework.meta.factories.ClassMetaSetupFactory;
import org.brijframework.meta.group.MetaInfoGroup;
import org.brijframework.support.model.Assignable;
import org.brijframework.util.reflect.InstanceUtil;
import org.brijframework.util.reflect.ReflectionUtils;

public class MetaSetupContainer extends AbstractContainer implements MetaContainer {

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
