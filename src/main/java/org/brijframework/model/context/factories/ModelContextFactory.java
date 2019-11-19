package org.brijframework.model.context.factories;

import org.brijframework.factories.BootstrapFactory;
import org.brijframework.factories.Factory;
import org.brijframework.factories.impl.AbstractFactory;
import org.brijframework.model.context.ModelContext;
import org.brijframework.support.config.Assignable;
import org.brijframework.util.reflect.InstanceUtil;
import org.brijframework.util.reflect.ReflectionUtils;

public class ModelContextFactory extends AbstractFactory<String, ModelContext> implements BootstrapFactory{
	
	private static ModelContextFactory factory ;
	
	@Assignable
	public static ModelContextFactory getFactory() {
		if(factory==null) {
		    factory=new ModelContextFactory();
		}
		return factory;
	}

	@Override
	public Factory loadFactory() {
		try {
			ReflectionUtils.getClassListFromExternal().forEach(cls->{
				if(ModelContext.class.isAssignableFrom(cls) && InstanceUtil.isAssignable(cls)) {
					ModelContext modelContext = (ModelContext) InstanceUtil.getInstance(cls);
					modelContext.start();
					this.register(modelContext.getClass().getName(), modelContext);
					this.register(modelContext.getClass().getSimpleName(), modelContext);
				}
			});
			ReflectionUtils.getClassListFromInternal().forEach(cls->{
				if(ModelContext.class.isAssignableFrom(cls) && InstanceUtil.isAssignable(cls)) {
					ModelContext modelContext = (ModelContext) InstanceUtil.getInstance(cls);
					modelContext.start();
					this.register(modelContext.getClass().getName(), modelContext);
					this.register(modelContext.getClass().getSimpleName(), modelContext);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	@Override
	protected void preregister(String key, ModelContext value) {
	}

	@Override
	protected void postregister(String key, ModelContext value) {
	}

	public ModelContext getModelContext() {
		return getCache().get(ModelContext.class.getName());
	}
	
	public ModelContext getModelContext(Class<? extends ModelContext> modelContextClass) {
		return getCache().get(modelContextClass.getName());
	}


}
