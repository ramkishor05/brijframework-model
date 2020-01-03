package org.brijframework.model.context.factories;

import org.brijframework.factories.impl.bootstrap.AbstractBootstrapFactory;
import org.brijframework.model.context.ModelContext;
import org.brijframework.support.factories.SingletonFactory;
import org.brijframework.support.ordering.OrderOn;
import org.brijframework.util.factories.ReflectionFactory;
import org.brijframework.util.printer.LoggerConsole;
import org.brijframework.util.reflect.InstanceUtil;

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
			LoggerConsole.screen("BootstrapFactory - > "+this.getClass().getSimpleName(), "Lunching model context factory to start the model context");
			ReflectionFactory.getFactory().getExternalClassList().forEach(cls->{
				if(ModelContext.class.isAssignableFrom(cls) && InstanceUtil.isAssignable(cls)) {
					ModelContext modelContext = (ModelContext) InstanceUtil.getInstance(cls);
					modelContext.start();
					this.register(modelContext.getClass().getName().equals(ModelContext.class.getName()+"Impl")?ModelContext.class.getName():modelContext.getClass().getName() , modelContext);
					this.register(modelContext.getClass().getSimpleName().equals(ModelContext.class.getSimpleName()+"Impl")?ModelContext.class.getSimpleName():modelContext.getClass().getSimpleName() , modelContext);
				}
			});
			ReflectionFactory.getFactory().getInternalClassList().forEach(cls->{
				if(ModelContext.class.isAssignableFrom(cls) && InstanceUtil.isAssignable(cls)) {
					ModelContext modelContext = (ModelContext) InstanceUtil.getInstance(cls);
					modelContext.start();
					this.register(modelContext.getClass().getName().equals(ModelContext.class.getName()+"Impl")?ModelContext.class.getName():modelContext.getClass().getName() , modelContext);
					this.register(modelContext.getClass().getSimpleName().equals(ModelContext.class.getSimpleName()+"Impl")?ModelContext.class.getSimpleName():modelContext.getClass().getSimpleName() , modelContext);
		    	}
			});
			LoggerConsole.screen("BootstrapFactory - > "+this.getClass().getSimpleName(), "Lunched model context factory to start the model context");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	@Override
	protected void preregister(String key, ModelContext value) {
		LoggerConsole.screen("ModelContext - > "+this.getClass().getSimpleName(), "Registering for model context with id "+key);
	}

	@Override
	protected void postregister(String key, ModelContext value) {
		LoggerConsole.screen("ModelContext - > "+this.getClass().getSimpleName(), "Registered for model context with id "+key);
	}

	public ModelContext getModelContext() {
		return getCache().get(ModelContext.class.getSimpleName());
	}
	
	public ModelContext getModelContext(Class<? extends ModelContext> modelContextClass) {
		return getCache().get(modelContextClass.getSimpleName());
	}


}
