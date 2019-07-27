package com.syp.le.utils;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class MapUtilTest {

	@Test
	public void shouldAdd() {
		// Given
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();

		// When
		MapUtil.add(map, "key1", null);
		MapUtil.add(map, "key2", "value2");

		// Then
		assertThat(map.get("key1")).isNull();
		assertThat(map.get("key2")).isNotNull();
	}
}
