package org.brijframework.meta.impl;

import java.util.Map;

import org.brijframework.meta.asm.AbstractConstMetaInfo;
import org.brijframework.meta.info.ClassMetaInfo;

public class ConstMetaInfoImpl extends AbstractConstMetaInfo{
	
	public ConstMetaInfoImpl(ClassMetaInfo owner) {
		this.setOwner(owner);
	}

	@Override
	public void papulate(Map<String, Object> map) {
	}
}
