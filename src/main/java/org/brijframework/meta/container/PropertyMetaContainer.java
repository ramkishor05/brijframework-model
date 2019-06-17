package org.brijframework.meta.container;

import org.brijframework.asm.container.AbstractContainer;
import org.brijframework.group.Group;
import org.brijframework.meta.factories.FieldMetaFactory;
import org.brijframework.meta.factories.MetaFactory;
import org.brijframework.meta.group.MetaGroup;
import org.brijframework.support.model.Assignable;
import org.brijframework.support.model.DepandOn;
import org.brijframework.util.reflect.InstanceUtil;
import org.brijframework.util.reflect.ReflectionUtils;

@DepandOn(depand=ClassMetaContainer.class)
public class PropertyMetaContainer extends AbstractContainer implements MetaContainer {

	private static PropertyMetaContainer container;

	@Assignable
	public static PropertyMetaContainer getContainer() {
		if (container == null) {
			container = InstanceUtil.getSingletonInstance(PropertyMetaContainer.class);
		}
		return container;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void init() {
		try {
			ReflectionUtils.getClassListFromExternal().forEach(cls -> {
				if (FieldMetaFactory.class.isAssignableFrom(cls) && InstanceUtil.isAssignable(cls)) {
					register((Class<? extends FieldMetaFactory<?>>) cls);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			ReflectionUtils.getClassListFromInternal().forEach(cls -> {
				if (FieldMetaFactory.class.isAssignableFrom(cls) && InstanceUtil.isAssignable(cls)) {
					register((Class<? extends MetaFactory<?>>) cls);
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
