package com.syp.le.utils;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.BeanUtilsBean2;

import com.syp.le.exception.ServerException;

/**
 * @author <a href="mailto:ricky.shiyouping@gmail.com">Ricky Shi</a>
 *
 * @since Jun 29, 2019
 */
public class BeanUtil {

	private final static MyBeanUtilsBean myBean = new MyBeanUtilsBean();
	private final static BeanUtilsBean2 defaultBean = new BeanUtilsBean2();

	private BeanUtil() {
	}

	/**
	 * Delegate of {@linkplain BeanUtilsBean#copyProperties(Object, Object)}.
	 * 
	 * @param destination Destination bean whose properties are modified
	 * @param source      Source bean whose properties are retrieved
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public static void copyAllProperties(final Object destination, final Object source) {
		try {
			defaultBean.copyProperties(destination, source);
		} catch (Exception e) {
			throw new ServerException("Cannot copy bean properties", e);
		}
	}

	/**
	 * See {@linkplain BeanUtilsBean#copyProperties(Object, Object)}. The only
	 * difference is that this implementation only copies non-null properties
	 * instead of all properties from source bean
	 * 
	 * @param destination Destination bean whose properties are modified
	 * @param source      Source bean whose properties are retrieved
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public static void copyNonnullProperties(final Object destination, final Object source) {
		try {
			myBean.copyProperties(destination, source);
		} catch (Exception e) {
			throw new ServerException("Cannot copy bean properties", e);
		}
	}

	private static class MyBeanUtilsBean extends BeanUtilsBean2 {
		@Override
		public void copyProperty(Object bean, String name, Object value)
				throws IllegalAccessException, InvocationTargetException {

			if (value == null) {
				return;
			}

			super.copyProperty(bean, name, value);
		}
	}
}
