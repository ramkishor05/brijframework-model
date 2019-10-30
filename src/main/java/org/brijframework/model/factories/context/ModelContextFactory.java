package org.brijframework.model.factories.context;

import org.brijframework.factories.Factory;
import org.brijframework.factories.impl.AbstractFactory;
import org.brijframework.model.context.ModelContext;

public class ModelContextFactory extends AbstractFactory<String, ModelContext>{
	
	private static ModelContextFactory factory;
	
	public static ModelContextFactory getFactory() {
		if(factory==null) {
			factory=new ModelContextFactory();
		}
		return factory;
	}

	@Override
	public Factory loadFactory() {
		return this;
	}

	@Override
	protected void preregister(String key, ModelContext value) {
		System.err.println("preregister  : "+key);
	}

	@Override
	protected void postregister(String key, ModelContext value) {
		System.err.println("postregister  : "+key);
	}

	public ModelContext getContext() {
		ModelContext context= getCache().get(ModelContext.class.getName());
		if(context!=null) {
			return context;
		}
		context=new ModelContext();
		context.init();
		context.start();
		register(ModelContext.class.getName(), context);
		return context;
	}
}
