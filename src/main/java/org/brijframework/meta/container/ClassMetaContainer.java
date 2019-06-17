package org.brijframework.meta.container;

import org.brijframework.asm.container.AbstractContainer;
import org.brijframework.group.Group;
import org.brijframework.meta.factories.ClassMetaFactory;
import org.brijframework.meta.group.MetaGroup;
import org.brijframework.support.model.Assignable;
import org.brijframework.util.reflect.InstanceUtil;
import org.brijframework.util.reflect.ReflectionUtils;

public class ClassMetaContainer extends AbstractContainer implements MetaContainer {

	private static ClassMetaContainer container;

	@Assignable
	public static ClassMetaContainer getContainer() {
		if (container == null) {
			container = InstanceUtil.getSingletonInstance(ClassMetaContainer.class);
		}
		return container;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void init() {
		try {
			ReflectionUtils.getClassListFromExternal().forEach(cls -> {
				if (ClassMetaFactory.class.isAssignableFrom(cls) && InstanceUtil.isAssignable(cls)) {
					register((Class<? extends ClassMetaFactory>) cls);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			ReflectionUtils.getClassListFromInternal().forEach(cls -> {
				if (ClassMetaFactory.class.isAssignableFrom(cls) && InstanceUtil.isAssignable(cls)) {
					register((Class<? extends ClassMetaFactory>) cls);
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
			group = new MetaGroup(groupKey);
			getCache().put(groupKey, group);
		}
		return group;
	}
}
