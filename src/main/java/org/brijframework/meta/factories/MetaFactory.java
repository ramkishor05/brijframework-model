package org.brijframework.meta.factories;

import java.util.concurrent.ConcurrentHashMap;

import org.brijframework.factories.Factory;
import org.brijframework.group.Group;
import org.brijframework.meta.KeyInfo;
import org.brijframework.meta.MetaInfo;

public interface MetaFactory<T extends MetaInfo<?>> extends Factory{

	@Override
	default MetaFactory<T> clear() {
		this.getCache().clear();
		return this;
	}
	
	ConcurrentHashMap<KeyInfo, ? extends MetaInfo<?>> getCache();
	
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
			Group group=getContainer().get(groupKey());
			group.marge(key, value);
		});
	}
	
	Object groupKey();

}
