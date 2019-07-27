package com.syp.le.utils;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class StringUtilTest {

	@Test
	public void shouldToString() {
		// Given
		Integer i1 = null;
		Integer i2 = 12;
		Boolean b1 = null;
		Boolean b2 = true;

		// When
		String s1 = StringUtil.toString(i1);
		String s2 = StringUtil.toString(i2);
		String s3 = StringUtil.toString(b1);
		String s4 = StringUtil.toString(b2);

		// Then
		assertThat(s1).isNull();
		assertThat(s2).isEqualTo("12");
		assertThat(s3).isNull();
		assertThat(s4).isEqualTo("true");
	}
}
