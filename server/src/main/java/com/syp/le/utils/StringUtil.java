package com.syp.le.utils;

import javax.annotation.Nullable;

/**
 * @author <a href="mailto:ricky.shiyouping@gmail.com">Ricky Shi</a>
 *
 * @since 26 Jul 2019
 */
public class StringUtil {

	private StringUtil() {

	}

	@Nullable
	public static String toString(@Nullable Integer integer) {
		if (integer == null) {
			return null;
		}

		return integer.toString();
	}

	@Nullable
	public static String toString(@Nullable Boolean bool) {
		if (bool == null) {
			return null;
		}

		return bool.toString();
	}
}
