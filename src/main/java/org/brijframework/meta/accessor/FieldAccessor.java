package org.brijframework.meta.accessor;

import org.brijframework.accessor.Accessor;
import org.brijframework.meta.MetaInfo;
import org.brijframework.meta.factories.FieldInfoFactory;

public interface FieldAccessor extends Accessor{
	
	public MetaInfo getReadInfo();
	
	public MetaInfo getWriteInfo();
	
	public MetaInfo getFieldInfo();
	
	public <T> T read(Object _instance); 

	public <T> T write(Object _instance, Object _value); 
	
	boolean isContainsReader();

	boolean isContainsWriter();
	
	FieldInfoFactory getInfoFactory();
	
	String getParentID();

}
