package org.brijframework.meta.helper;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import org.brijframework.container.Container;
import org.brijframework.meta.impl.ClassKey;
import org.brijframework.meta.impl.ModelConsMeta;
import org.brijframework.meta.impl.ModelMeta;
import org.brijframework.meta.reflect.ClassMeta;
import org.brijframework.meta.setup.ClassMetaSetup;
import org.brijframework.support.enums.Scope;
import org.brijframework.support.model.Construct;
import org.brijframework.support.model.Model;
import org.brijframework.util.casting.CastingUtil;
import org.brijframework.util.reflect.ClassUtil;
import org.brijframework.util.reflect.ConstructUtil;
import org.brijframework.util.support.Access;
import org.brijframework.util.support.Constants;

public class ModelMetaHelper {

	public static ClassMeta getModelInfo(Container container,Class<?> target,ClassMetaSetup metaSetup) {
		Objects.requireNonNull(metaSetup, "MetaSetup should be required");
		if(target==null) {
			target=ClassUtil.getClass(metaSetup.getTarget());
		}
		Objects.requireNonNull(target, "Target should be required");
		String id=metaSetup.getId()==null|| metaSetup.getId().equals(Constants.DEFAULT)?target.getSimpleName():metaSetup.getId();
		String name=metaSetup.getName()==null|| metaSetup.getName().equals(Constants.DEFAULT)?target.getSimpleName():metaSetup.getName();
		ModelMeta owner=new ModelMeta(target,id,name);
		owner.setAccess(metaSetup.getAccess()!=null?Access.valueOf(metaSetup.getAccess()):Access.DEFAULT);
		owner.setScope(metaSetup.getScope()!=null?Scope.valueOf(metaSetup.getScope()):Scope.SINGLETON);
		/*if(metaSetup.constructor()!=null) {
			owner.setConstructor(getConsMetaInfo(owner, metaSetup.constructor()));
		}else {
			owner.setConstructor(getConsMetaInfo(owner));
		}*/
		ClassKey keyInfo=new ClassKey(owner);
		keyInfo.init();
		return owner;
	}
	
	public static ClassMeta getModelInfo(Container container,Class<?> target,Model metaSetup) {
		Objects.requireNonNull(target, "Target should be required");
		Objects.requireNonNull(metaSetup, "Meta should be required");
		String id=metaSetup.id()==null|| metaSetup.id().equals(Constants.DEFAULT)?target.getSimpleName():metaSetup.id();
		String name=target.getSimpleName();
		ModelMeta owner=new ModelMeta(target,id,name);
		owner.setAccess(metaSetup.access()!=null?metaSetup.access():Access.DEFAULT);
		owner.setScope(metaSetup.scope()!=null?metaSetup.scope():Scope.SINGLETON);
		if(metaSetup.constructor()!=null) {
			owner.setConstructor(getConsMetaInfo(owner, metaSetup.constructor()));
		}else {
			owner.setConstructor(getConsMetaInfo(owner));
		}
		ClassKey keyInfo=new ClassKey(owner);
		keyInfo.init();
		return owner;
	}
	
	public static ModelConsMeta getConsMetaInfo(ModelMeta owner) {
		ModelConsMeta consMetaInfo=new ModelConsMeta(owner);
		consMetaInfo.setAccess(owner.getAccess());
		consMetaInfo.setScope(owner.getScope());
		consMetaInfo.setOwner(owner);
		consMetaInfo.setTarget(ConstructUtil.getConstructor(owner.getTarget(), owner.getAccess(), consMetaInfo.getArguments()));
		return consMetaInfo;
	}
	
	public static ModelConsMeta getConsMetaInfo(ModelMeta owner,Construct constructor) {
		ModelConsMeta consMetaInfo=new ModelConsMeta(owner);
		consMetaInfo.setAccess(constructor.access());
		consMetaInfo.setScope(constructor.scope());
		consMetaInfo.setOwner(owner);
		if(constructor.params()!=null) {
			Type[] arguments=new Type[constructor.params().length];
			Object[] values=new Object[constructor.params().length];
			AtomicInteger index=new AtomicInteger(0);
			Arrays.asList(constructor.params()).stream().sorted((param1,param2)->{
				if(param1.index()>param2.index()) {
					return 1;
				}
				if(param1.index()<param2.index()) {
					return -1;
				}
				return 0;
			}).forEach(param->{
				arguments[index.get()]=param.type();
				values[index.getAndIncrement()]=CastingUtil.castObject(param.value(), param.type());
			});
			consMetaInfo.setArguments(arguments);
			consMetaInfo.setValues(values);
		}
		consMetaInfo.setTarget(ConstructUtil.getConstructor(owner.getTarget(), constructor.access(), consMetaInfo.getArguments()));
		return consMetaInfo;
	}
}
