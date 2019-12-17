package org.brijframework.model.mapper.model;

import java.lang.reflect.Field;

import org.brijframework.model.diffination.ModelTypeDiffination;
import org.brijframework.model.diffination.asm.AbstractModelDiffination;

public class PropertyModelMapperResource extends AbstractModelDiffination<Field> {

	private String source;

	private String destination;

	private Field target;

	ModelTypeDiffination owner;

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

	public void setOwner(ModelTypeDiffination owner) {
		this.owner = owner;
	}
	
	public ModelTypeDiffination getOwner() {
		return owner;
	}

}
