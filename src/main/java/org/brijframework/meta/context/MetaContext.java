package org.brijframework.meta.context;

import org.brijframework.asm.context.DefaultContainerContext;
import org.brijframework.meta.container.MetaContainer;
import org.brijframework.resources.context.ResourceContext;
import org.brijframework.support.model.DepandOn;
import org.brijframework.support.util.SupportUtil;
import org.brijframework.util.reflect.ReflectionUtils;

@DepandOn(depand=ResourceContext.class)
public class MetaContext extends DefaultContainerContext{

	@Override
	@SuppressWarnings("unchecked")
	public void init() {
		try {
			ReflectionUtils.getClassListFromExternal().forEach(cls->{
				if(MetaContainer.class.isAssignableFrom(cls)) {
					register((Class<? extends MetaContainer>) cls);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			ReflectionUtils.getClassListFromInternal().forEach(cls->{
				if(MetaContainer.class.isAssignableFrom(cls)) {
					register((Class<? extends MetaContainer>) cls);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void startup() {
		System.err.println("MetaContext register start.");
		SupportUtil.getDepandOnSortedClassList(getClassList()).forEach((container) -> {
			loadContainer(container);
		});
		System.err.println("MetaContext register done.");
	}

	@Override
	public void destory() {
		System.err.println("MetaContext destory start.");
		SupportUtil.getDepandOnSortedClassList(getClassList()).forEach((container) -> {
			destoryContainer(container);
		});
		System.err.println("MetaContext destory done.");
	}
}
