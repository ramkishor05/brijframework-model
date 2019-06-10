package org.brijframework.meta.util;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Map;

import org.brijframework.util.accessor.MetaAccessorUtil;
import org.brijframework.util.meta.PointUtil;
import org.brijframework.util.reflect.ClassUtil;
import org.brijframework.util.reflect.FieldUtil;
import org.brijframework.util.support.Access;
import org.brijframework.util.support.Constants;

public class MetaBuilderUtil {

	public static Class<?> getCurrentClass(Class<?> cls, String[] keyArray) {
		Class<?> current = cls;
		Field field = null;
		for (int i = 0; i < keyArray.length - 1; i++) {
			String key = keyArray[i];
			if (key.contains(Constants.OPEN_BRAKET) && key.contains(Constants.CLOSE_BRAKET)) {
				current = findCurrentFromList(current,field, PointUtil.keyArray(key), PointUtil.indexArray(key), key);
			} else if (Map.class.isAssignableFrom(current)) {
				current = findCurrentFromMap(current, field, key);
			} else {
				field = FieldUtil.getField(current.getClass(), key, Access.PRIVATE);
				current = findCurrentFromObject(current, key);
			}
		}
		return current;
	}


	private static Class<?> findCurrentFromList(Class<?> current, Field field, String keyArray, Integer indexArray,
			String key) {
		return ClassUtil.collectionParamType(field);
	}

	private static Class<?> findCurrentFromObject(Class<?> current, String key) {
		Field field=MetaAccessorUtil.getFieldMeta(current, key,  Access.PRIVATE);
		if(field==null) {
			return null;
		}
		return field.getType();
	}

	private static Class<?> findCurrentFromMap(Class<?> current, Field field, String key) {
		ParameterizedType type = (ParameterizedType) field.getGenericType();
		Class<?> keyClass = (Class<?>) type.getActualTypeArguments()[0];
	    Class<?> valueClass = (Class<?>) type.getActualTypeArguments()[1];
		return valueClass;
	}


	public static Class<?> typeOfProperty(Class<? extends Object> _class, String _keyPath) {
		String[] keyArray = _keyPath.split(Constants.SPLIT_DOT);
		Class<?> current=getCurrentClass(_class, keyArray);
		if(current!=null) {
			return typeProperty(current,Access.PRIVATE, keyArray[keyArray.length-1]);
		}
		return null;
	}


	private static Class<?> typeProperty(Class<?> current, Access access, String key) {
		Field field=MetaAccessorUtil.getFieldMeta(current, key,  Access.PRIVATE);
		if(field==null) {
			return null;
		}
		return field.getType();
	}

}
