package org.brijframework.meta.container;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.brijframework.asm.container.DefaultContainer;
import org.brijframework.container.Container;
import org.brijframework.group.Group;
import org.brijframework.meta.factories.MetaFactory;
import org.brijframework.meta.group.MetaGroup;
import org.brijframework.support.model.Assignable;
import org.brijframework.support.model.DepandOn;
import org.brijframework.util.reflect.InstanceUtil;
import org.brijframework.util.reflect.MethodUtil;
import org.brijframework.util.reflect.ReflectionUtils;

public class MetaContainer implements DefaultContainer{
	
	private ConcurrentHashMap<Object, Group> cache=new ConcurrentHashMap<>();
	
	private static MetaContainer container;

	@Assignable
	public static MetaContainer getContainer() {
		if (container == null) {
			container = InstanceUtil.getSingletonInstance(MetaContainer.class);
			container.loadContainer();
		}
		return container;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Container loadContainer() {
		List<Class<? extends MetaFactory<?>>> classes=new ArrayList<>();
		try {
			ReflectionUtils.getClassListFromExternal().forEach(cls->{
				if(MetaFactory.class.isAssignableFrom(cls) && !cls.isInterface() && cls.getModifiers() != Modifier.ABSTRACT) {
					classes.add((Class<? extends MetaFactory<?>>) cls);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			ReflectionUtils.getClassListFromInternal().forEach(cls->{
				if(MetaFactory.class.isAssignableFrom(cls) && !cls.isInterface() && cls.getModifiers() != Modifier.ABSTRACT) {
					classes.add((Class<? extends MetaFactory<?>>) cls);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		classes.forEach((metaFactory)->{
			if(metaFactory.isAnnotationPresent(DepandOn.class)) {
			   DepandOn depandOn=metaFactory.getAnnotation(DepandOn.class);
			   loading(depandOn.depand());
			}
			loading(metaFactory);
		});
		return container;
	}

	private void loading(Class<?> cls) {
		boolean called=false;
		for(Method method:MethodUtil.getAllMethod(cls)) {
			if(method.isAnnotationPresent(Assignable.class)) {
				try {
					method.invoke(null);
					called=true;
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		if(!called) {
			try {
				MetaFactory<?> container=(MetaFactory<?>) cls.newInstance();
				container.loadFactory();
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public ConcurrentHashMap<Object, Group> getCache() {
		return cache;
	}

	@Override
	public Group load(Object groupKey) {
		Group group= getCache().get(groupKey);
		if(group==null) {
			group=new MetaGroup(groupKey);
			getCache().put(groupKey, group);
		}
		return group;
	}

}
