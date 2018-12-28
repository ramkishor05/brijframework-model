package org.brijframework.meta.factories;

import java.util.concurrent.ConcurrentHashMap;

import org.brijframework.factories.Factory;
import org.brijframework.group.Group;
import org.brijframework.meta.KeyInfo;
import org.brijframework.meta.MetaInfo;

public interface MetaInfoFactory<T extends MetaInfo> extends Factory{

	@Override
	default MetaInfoFactory<T> clear() {
		this.getCache().clear();
		return this;
	}
	
	ConcurrentHashMap<KeyInfo,T> getCache();
	
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
