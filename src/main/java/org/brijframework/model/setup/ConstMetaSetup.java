package org.brijframework.model.setup;

import java.util.List;

import org.brijframework.model.info.ParameterModelInfo;
import org.brijframework.util.support.Access;

public interface ConstMetaSetup {

	List<ParameterModelInfo> getParams();

	Access getAccess();

}
