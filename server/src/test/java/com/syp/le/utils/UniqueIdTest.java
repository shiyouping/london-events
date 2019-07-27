package com.syp.le.utils;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class UniqueIdTest {

	@Test
	public void shouldGet() {
		String id = UniqueId.get();
		assertThat(id).doesNotContain("-");
	}
}
