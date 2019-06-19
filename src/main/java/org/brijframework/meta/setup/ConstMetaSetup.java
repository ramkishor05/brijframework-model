package org.brijframework.meta.setup;

import java.util.List;

import org.brijframework.meta.info.ParamMetaSetup;
import org.brijframework.util.support.Access;

public interface ConstMetaSetup {

	List<ParamMetaSetup> getParams();

	Access getAccess();

}
