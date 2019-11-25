package org.brijframework.model.helper;

import org.brijframework.model.resource.ClassModelResource;
import org.brijframework.util.reflect.InstanceUtil;

public class MetaResourceHelper {

	public ClassModelResource buildClassMetaSetup(Class<ClassModelResource>_class) {
		ClassModelResource owner=InstanceUtil.getInstance(_class);
		
		
		return owner;
	}
}
