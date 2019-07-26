package com.syp.le.utils;

import javax.annotation.Nullable;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.MultiValueMap;

/**
 * @author <a href="mailto:ricky.shiyouping@gmail.com">Ricky Shi</a>
 *
 * @since 26 Jul 2019
 */
public class MapUtil {

	private MapUtil() {
	};

	/**
	 * Add the key-value pair into the map if the value is not null
	 */
	public static void add(@Nullable MultiValueMap<String, String> map, @Nullable String key, @Nullable String value) {
		if (map == null || StringUtils.isBlank(key) || StringUtils.isBlank(value)) {
			return;
		}

		map.add(key, value);
	}
}
