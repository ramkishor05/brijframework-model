package org.brijframework.model.info.impl;

import java.util.Map;

import org.brijframework.model.info.OwnerModelInfo;
import org.brijframework.model.info.asm.AbstractConstModelInfo;

public class ConstMetaInfoImpl extends AbstractConstModelInfo{
	
	public ConstMetaInfoImpl(OwnerModelInfo owner) {
		this.setOwner(owner);
	}

	@Override
	public void papulate(Map<String, Object> map) {
	}
}
