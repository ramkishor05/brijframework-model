package org.brijframework.meta.util;

import org.brijframework.util.asserts.Assertion;
import org.brijframework.util.meta.PointUtil;
import org.brijframework.util.support.Constants;

public class MetaBuilderUtil {
	
	public static Class<?> getCurrentClass(Class<?> cls, String _keyPath) {
		String[] keyArray=_keyPath.split(Constants.SPLIT_DOT);
		Class<?> keyClass=cls;
		for (int i = 0; i < keyArray.length-1; i++) {
			String key = keyArray[i];
			//keyClass=getProperty(keyClass, key);
		}
		return keyClass;
	}
	
	
	
}
