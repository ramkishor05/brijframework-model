package org.brijframework.meta.asm.annotation;

import java.lang.reflect.AccessibleObject;

import org.brijframework.meta.reflect.ClassMetaInfo;
import org.brijframework.support.enums.Wiring;

public class RelationMetaInfo extends PropertyMetaInfo{

	Class<?> mappedTo;
	
	String mappedBy;
	
	Wiring referred;
	
	public RelationMetaInfo(AccessibleObject target) {
		super(target);
	}
	
	public RelationMetaInfo(ClassMetaInfo owner, AccessibleObject target) {
		super(owner,target);
	}

}
