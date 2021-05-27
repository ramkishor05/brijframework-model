package org.brijframework.model.mapper;

import org.brijframework.model.mapper.factories.impl.TypeModelMapperResourceImpl;
import org.brijframework.model.mapper.model.TypeModelMapperResource;

public abstract class GenericMapper<T,S> {

	public T target(S source) {
		TypeModelMapperResource model= TypeModelMapperResourceImpl.getFactory().find(source.getClass().getName());
		System.out.println(model);
		return null;
	}
	
	public S source(T target) {
		TypeModelMapperResource model= TypeModelMapperResourceImpl.getFactory().find(target.getClass().getName());
		System.out.println(""+model);
		return null;
	}
}
