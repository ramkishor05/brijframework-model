package org.brijframework.model.context;

import org.brijframework.asm.context.AbstractModuleContext;
import org.brijframework.model.container.ModelContainer;
import org.brijframework.resources.context.ResourceContext;
import org.brijframework.support.config.DepandOn;
import org.brijframework.util.reflect.InstanceUtil;
import org.brijframework.util.reflect.ReflectionUtils;

@DepandOn(depand=ResourceContext.class)
public class ModelContext extends AbstractModuleContext{

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
	
	@Override
	public void start() {
		System.err.println("MetaContext register start.");
		super.start();
		System.err.println("MetaContext register done.");
	}

	@Override
	public void stop() {
		System.err.println("MetaContext destory start.");
		super.stop();
		System.err.println("MetaContext destory done.");
	}
}
