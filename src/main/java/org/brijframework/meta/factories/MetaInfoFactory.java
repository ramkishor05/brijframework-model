package org.brijframework.meta.factories;

import java.util.concurrent.ConcurrentHashMap;

import org.brijframework.factories.Factory;
import org.brijframework.group.Group;
import org.brijframework.meta.KeyInfo;
import org.brijframework.meta.MetaInfo;
import org.brijframework.util.reflect.PackUtil;

public interface MetaInfoFactory extends Factory{

	@Override
	default MetaInfoFactory loadFactory() {
		this.clear();
		PackUtil.getProjectClasses().forEach(cls -> {
			this.register(cls);
		});
		this.loadToContainer();
		return this;
	}

	void register(Class<?> cls);

	@Override
	default MetaInfoFactory clear() {
		this.getCache().clear();
		return this;
	}
	
	@Override
	default String getJson() {
		return null;
	}
	
	ConcurrentHashMap<KeyInfo,? extends MetaInfo> getCache();
	
	/**
	 * <pre>Load meta data to globel cache<br></pre>
	 * 
	 */
	default void loadToContainer() {
		if(getContainer() ==null) {
			System.err.println("No container find");
			return ;
		}
		this.getCache().forEach((key,value)->{
			Group group=getContainer().load(groupKey());
			group.marge(key, value);
		});
	}
	
	Object groupKey();
}
