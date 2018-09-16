package org.brijframework.meta.accessor;

import org.brijframework.accessor.Accessor;
import org.brijframework.meta.blueprint.ClassInfo;
import org.brijframework.meta.factories.ClassInfoFactory;

public interface ClassAccessor extends Accessor {

	public <T> T getInstanse(Object...objects);

	public ClassInfo getClassInfo();

	public ClassInfo getClassInfo(String parentID);
	
	public ClassInfoFactory getInfoFactory();

}
