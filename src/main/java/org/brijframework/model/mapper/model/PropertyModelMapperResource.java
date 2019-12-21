package org.brijframework.model.mapper.model;

import java.lang.reflect.Field;

import org.brijframework.model.diffination.ModelTypeDeffination;
import org.brijframework.model.diffination.asm.AbstractModelDiffination;

public class PropertyModelMapperResource extends AbstractModelDiffination<Field> {

	private String source;

	private String destination;

	private Field target;

	ModelTypeDeffination owner;

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

	public void setOwner(ModelTypeDeffination owner) {
		this.owner = owner;
	}
	
	public ModelTypeDeffination getOwner() {
		return owner;
	}

}
