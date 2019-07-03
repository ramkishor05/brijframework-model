package org.brijframework.model.group;

import java.util.concurrent.ConcurrentHashMap;

import org.brijframework.asm.group.DefaultGroup;
import org.brijframework.model.ModelInfo;
import org.brijframework.util.asserts.Assertion;

public class MetaInfoGroup implements DefaultGroup{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Object groupKey;
	
	private ConcurrentHashMap<String,ModelInfo<?>> cache=new ConcurrentHashMap<>();
	
	public MetaInfoGroup(Object groupKey) {
		this.groupKey=groupKey;
		Assertion.notNull(this.groupKey, "Group key is required.");
	}

	@Override
	public Object getGroupKey() {
		return groupKey;
	}

	@Override
	public ConcurrentHashMap<String,ModelInfo<?>> getCache() {
		return cache;
	}

	@Override
	public <T> T find(String parentID, Class<?> type) {
		return null;
	}

}
