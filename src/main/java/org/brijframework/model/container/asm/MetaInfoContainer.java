package org.brijframework.model.container.asm;

import org.brijframework.asm.container.AbstractContainer;
import org.brijframework.group.Group;
import org.brijframework.model.container.ModelContainer;
import org.brijframework.model.factories.ClassMetaInfoFactory;
import org.brijframework.model.group.MetaInfoGroup;
import org.brijframework.support.config.Assignable;
import org.brijframework.support.config.DepandOn;
import org.brijframework.util.reflect.InstanceUtil;
import org.brijframework.util.reflect.ReflectionUtils;

@DepandOn(depand=MetaSetupContainer.class)
public class MetaInfoContainer extends AbstractContainer implements ModelContainer {

	private static MetaInfoContainer container;

	@Assignable
	public static MetaInfoContainer getContainer() {
		if (container == null) {
			container = InstanceUtil.getSingletonInstance(MetaInfoContainer.class);
		}
		return container;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void init() {
		try {
			ReflectionUtils.getClassListFromExternal().forEach(cls -> {
				if (ClassMetaInfoFactory.class.isAssignableFrom(cls) && InstanceUtil.isAssignable(cls)) {
					register((Class<? extends ClassMetaInfoFactory>) cls);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			ReflectionUtils.getClassListFromInternal().forEach(cls -> {
				if (ClassMetaInfoFactory.class.isAssignableFrom(cls) && InstanceUtil.isAssignable(cls)) {
					register((Class<? extends ClassMetaInfoFactory>) cls);
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
