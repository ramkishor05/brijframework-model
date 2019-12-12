package org.brijframework.model.mapper.model;

import java.lang.reflect.Field;

import org.brijframework.model.diffination.TypeModelDiffination;
import org.brijframework.model.diffination.asm.AbstractModelMetaData;

public class PropertyModelMapperResource extends AbstractModelMetaData<Field> {

	private String source;

	private String destination;

	private Field target;

	TypeModelDiffination owner;

	@Override
	public Field getType() {
		return target;
	}

	public void setTarget(Field target) {
		this.target = target;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public void setOwner(TypeModelDiffination owner) {
		this.owner = owner;
	}
	
	public TypeModelDiffination getOwner() {
		return owner;
	}

}
