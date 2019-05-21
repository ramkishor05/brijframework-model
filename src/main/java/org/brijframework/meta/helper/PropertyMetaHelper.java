package org.brijframework.meta.helper;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.brijframework.meta.impl.PropertyKey;
import org.brijframework.meta.impl.PropertyMeta;
import org.brijframework.meta.impl.RelPtpMeta;
import org.brijframework.meta.reflect.ClassMeta;
import org.brijframework.meta.setup.FieldMetaSetup;
import org.brijframework.meta.setup.RelationMetaSetup;
import org.brijframework.support.model.Property;
import org.brijframework.support.model.Relation;
import org.brijframework.util.support.Access;
import org.brijframework.util.support.Constants;

public class PropertyMetaHelper {
	
	public static RelPtpMeta getPropertyMetaInfo(ClassMeta owner, AccessibleObject target,
			Relation property) {
		if (target instanceof Field) {
			return getPropertyMetaInfo(owner, (Field) target, property);
		} else {
			return getPropertyMetaInfo(owner, (Method) target, property);
		}
	}

	public static PropertyMeta getPropertyMetaInfo(ClassMeta owner, AccessibleObject target,
			Property property) {
		if (target instanceof Field) {
			return getPropertyMetaInfo(owner, (Field) target, property);
		} else {
			return getPropertyMetaInfo(owner, (Method) target, property);
		}
	}
	
	public static RelPtpMeta getPropertyMetaInfo(ClassMeta owner, Method target,Relation property) {
		String id = property.id() == null || property.id().equals(Constants.DEFAULT)? target.getName() : property.id();
		RelPtpMeta metaInfo = new RelPtpMeta(owner, target);
		metaInfo.setId(id);
		String name=target.getName();
		metaInfo.setName(name);
		metaInfo.setAccess(property.access());
		metaInfo.setScope(property.scope());
		metaInfo.setValue(property.value());
		metaInfo.setRequired(property.required());
		PropertyKey keyInfo=new PropertyKey(metaInfo);
		keyInfo.init();
		return metaInfo;
	}
	
	public static RelPtpMeta getPropertyMetaInfo(ClassMeta owner, Field target,Relation property) {
		String id = property.id() == null || property.id().equals(Constants.DEFAULT)? target.getName() : property.id();
		RelPtpMeta metaInfo = new RelPtpMeta(owner, target);
		metaInfo.setId(id);
		String name=target.getName();
		metaInfo.setName(name);
		metaInfo.setAccess(property.access());
		metaInfo.setScope(property.scope());
		metaInfo.setValue(property.value());
		metaInfo.setRequired(property.required());
		metaInfo.setMappedBy(property.mappedBy());
		PropertyKey keyInfo=new PropertyKey(metaInfo);
		keyInfo.init();
		return metaInfo;
	}
	
	
	public static PropertyMeta getPropertyMetaInfo(ClassMeta owner, Field target,Property property) {
		String id = property.id() == null || property.id().equals(Constants.DEFAULT)? target.getName() : property.id();
		PropertyMeta metaInfo = new PropertyMeta(owner, target);
		metaInfo.setId(id);
		String name=target.getName();
		metaInfo.setName(name);
		metaInfo.setAccess(property.access());
		metaInfo.setScope(property.scope());
		metaInfo.setValue(property.value());
		metaInfo.setRequired(property.required());
		PropertyKey keyInfo=new PropertyKey(metaInfo);
		keyInfo.init();
		return metaInfo;
	}
	
	public static PropertyMeta getPropertyMetaInfo(ClassMeta owner, Method target,Property property) {
		String id = property.id() == null || property.id().equals(Constants.DEFAULT)? target.getName() : property.id();
		PropertyMeta metaInfo = new PropertyMeta(owner, target);
		metaInfo.setId(id);
		String name=target.getName();
		metaInfo.setName(name);
		metaInfo.setAccess(property.access());
		metaInfo.setScope(property.scope());
		metaInfo.setValue(property.value());
		metaInfo.setRequired(property.required());
		PropertyKey keyInfo=new PropertyKey(metaInfo);
		keyInfo.init();
		return metaInfo;
	}
	
	public static PropertyMeta getPropertyMetaInfo(ClassMeta owner, Method target,FieldMetaSetup property) {
		String id = property.getId() == null || property.getId().equals(Constants.DEFAULT)? target.getName() : property.getId();
		PropertyMeta metaInfo = new PropertyMeta(owner, target);
		metaInfo.setId(id);
		String name=target.getName();
		metaInfo.setName(name);
		metaInfo.setAccess(Access.valueOf(property.getAccess()));
		metaInfo.setValue(property.getValue());
		metaInfo.setRequired(property.isRequired());
		PropertyKey keyInfo=new PropertyKey(metaInfo);
		keyInfo.init();
		return metaInfo;
	}
	
	public static PropertyMeta getPropertyMetaInfo(ClassMeta owner, Field target,FieldMetaSetup property) {
		String id = property.getId() == null || property.getId().equals(Constants.DEFAULT)? target.getName() : property.getId();
		PropertyMeta metaInfo = new PropertyMeta(owner, target);
		metaInfo.setId(id);
		String name=target.getName();
		metaInfo.setName(name);
		metaInfo.setAccess(Access.valueOf(property.getAccess()));
		metaInfo.setRequired(property.isRequired());
		PropertyKey keyInfo=new PropertyKey(metaInfo);
		keyInfo.init();
		return metaInfo;
	}
	
	public static RelPtpMeta getPropertyMetaInfo(ClassMeta owner, Field target,RelationMetaSetup property) {
		String id = property.getId() == null || property.getId().equals(Constants.DEFAULT)? target.getName() : property.getId();
		RelPtpMeta metaInfo = new RelPtpMeta(owner, target);
		metaInfo.setId(id);
		String name=target.getName();
		metaInfo.setName(name);
		metaInfo.setAccess(Access.valueOf(property.getAccess()));
		metaInfo.setValue(property.getValue());
		metaInfo.setRequired(property.isRequired());
		PropertyKey keyInfo=new PropertyKey(metaInfo);
		keyInfo.init();
		return metaInfo;
	}
	
	public static RelPtpMeta getPropertyMetaInfo(ClassMeta owner, Method target,RelationMetaSetup property) {
		String id = property.getId() == null || property.getId().equals(Constants.DEFAULT)? target.getName() : property.getId();
		RelPtpMeta metaInfo = new RelPtpMeta(owner, target);
		metaInfo.setId(id);
		String name=target.getName();
		metaInfo.setName(name);
		metaInfo.setAccess(Access.valueOf(property.getAccess()));
		metaInfo.setValue(property.getValue());
		metaInfo.setRequired(property.isRequired());
		PropertyKey keyInfo=new PropertyKey(metaInfo);
		keyInfo.init();
		return metaInfo;
	}

	public static PropertyMeta getPropertyMetaInfo(ClassMeta owner, AccessibleObject target,
			FieldMetaSetup metaSetup) {
		if (target instanceof Field) {
			return getPropertyMetaInfo(owner, (Field) target, metaSetup);
		} else {
			return getPropertyMetaInfo(owner, (Method) target, metaSetup);
		}
	}
	
	public static RelPtpMeta getPropertyMetaInfo(ClassMeta owner, AccessibleObject target,
			RelationMetaSetup metaSetup) {
		if (target instanceof Field) {
			return getPropertyMetaInfo(owner, (Field) target, metaSetup);
		} else {
			return getPropertyMetaInfo(owner, (Method) target, metaSetup);
		}
	}
}
