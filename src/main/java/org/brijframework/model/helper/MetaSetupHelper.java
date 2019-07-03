package org.brijframework.model.helper;

import org.brijframework.model.setup.ClassMetaSetup;
import org.brijframework.util.reflect.InstanceUtil;

public class MetaSetupHelper {

	public ClassMetaSetup buildClassMetaSetup(Class<ClassMetaSetup>_class) {
		ClassMetaSetup owner=InstanceUtil.getInstance(_class);
		
		
		return owner;
	}
}
