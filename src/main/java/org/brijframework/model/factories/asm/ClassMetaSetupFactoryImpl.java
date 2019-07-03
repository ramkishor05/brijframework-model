package org.brijframework.model.factories.asm;

import java.util.List;
import java.util.Map.Entry;

import org.brijframework.model.factories.ClassMetaSetupFactory;
import org.brijframework.model.setup.ClassMetaSetup;
import org.brijframework.support.config.Assignable;

public class ClassMetaSetupFactoryImpl extends MetaSetupFactoryImpl<ClassMetaSetup> implements ClassMetaSetupFactory {
	
	protected ClassMetaSetupFactoryImpl() {
	}

	protected static ClassMetaSetupFactoryImpl factory;

	@Assignable
	public static ClassMetaSetupFactoryImpl getFactory() {
		if (factory == null) {
			factory = new ClassMetaSetupFactoryImpl();
		}
		return factory;
	}

	@Override
	public ClassMetaSetupFactoryImpl clear() {
		if (getCache() != null) {
			getCache().clear();
		}
		return this;
	}

	public ClassMetaSetup getClassInfo(String id) {
		for(Entry<String, ClassMetaSetup> entry:getCache().entrySet()) {
			if(entry.getKey().equals(id)) {
				return entry.getValue();
			}
		}
		return getContainer(id);
	}

	@Override
	public ClassMetaSetupFactoryImpl loadFactory() {
		return this;
	}

	@Override
	public List<ClassMetaSetup> getClassSetupList(Class<?> clsName) {
		return null;
	}

	@Override
	public List<ClassMetaSetup> getClassSetupList(Class<?> target, String parentID) {
		return null;
	}

	@Override
	public ClassMetaSetup getClassSetup(String id) {
		return null;
	}

}