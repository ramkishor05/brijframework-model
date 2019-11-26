package org.brijframework.model.context.factories;

import org.brijframework.factories.impl.bootstrap.AbstractBootstrapFactory;
import org.brijframework.model.context.ModelContext;
import org.brijframework.support.config.SingletonFactory;
import org.brijframework.support.config.OrderOn;
import org.brijframework.util.printer.ConsolePrint;
import org.brijframework.util.reflect.InstanceUtil;
import org.brijframework.util.reflect.ReflectionUtils;

@OrderOn(2)
public class ModelContextFactory extends AbstractBootstrapFactory<String, ModelContext>{
	
	private static ModelContextFactory factory ;
	
	@SingletonFactory
	public static ModelContextFactory getFactory() {
		if(factory==null) {
		    factory=new ModelContextFactory();
		}
		return factory;
	}

	@Override
	public ModelContextFactory loadFactory() {
		try {
			ConsolePrint.screen("BootstrapFactory - > "+this.getClass().getSimpleName(), "Lunching model context factory to start the model context");
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
			ConsolePrint.screen("BootstrapFactory - > "+this.getClass().getSimpleName(), "Lunched model context factory to start the model context");
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
