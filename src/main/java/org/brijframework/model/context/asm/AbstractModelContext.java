package org.brijframework.model.context.asm;

import org.brijframework.context.impl.module.AbstractModuleContext;
import org.brijframework.model.container.ModelContainer;
import org.brijframework.model.context.ModelContext;
import org.brijframework.util.reflect.InstanceUtil;
import org.brijframework.util.reflect.ReflectionUtils;

public abstract class AbstractModelContext extends AbstractModuleContext implements ModelContext{

	@Override
	@SuppressWarnings("unchecked")
	public void init() {
		try {
			ReflectionUtils.getClassListFromExternal().forEach(cls->{
				if(ModelContainer.class.isAssignableFrom(cls) && InstanceUtil.isAssignable(cls)) {
					register((Class<? extends ModelContainer>) cls);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			ReflectionUtils.getClassListFromInternal().forEach(cls->{
				if(ModelContainer.class.isAssignableFrom(cls) && InstanceUtil.isAssignable(cls)) {
					register((Class<? extends ModelContainer>) cls);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}