package org.brijframework.model.context.asm;

import org.brijframework.context.impl.module.AbstractModuleContext;
import org.brijframework.model.container.ModelContainer;
import org.brijframework.model.context.ModelContext;
import org.brijframework.util.factories.ReflectionFactory;
import org.brijframework.util.reflect.InstanceUtil;

public abstract class AbstractModelContext extends AbstractModuleContext implements ModelContext{

	@Override
	@SuppressWarnings("unchecked")
	public void init() {
		try {
			ReflectionFactory.getFactory().getExternalClassList().forEach(cls->{
				if(ModelContainer.class.isAssignableFrom(cls) && InstanceUtil.isAssignable(cls)) {
					register((Class<? extends ModelContainer>) cls);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			ReflectionFactory.getFactory().getInternalClassList().forEach(cls->{
				if(ModelContainer.class.isAssignableFrom(cls) && InstanceUtil.isAssignable(cls)) {
					register((Class<? extends ModelContainer>) cls);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
