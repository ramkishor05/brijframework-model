package org.brijframework.model.helper;

import org.brijframework.model.resource.TypeModelResource;
import org.brijframework.util.reflect.InstanceUtil;

public class MetaResourceHelper {

	public TypeModelResource buildClassMetaSetup(Class<TypeModelResource>_class) {
		TypeModelResource owner=InstanceUtil.getInstance(_class);
		
		
		return owner;
	}
}
