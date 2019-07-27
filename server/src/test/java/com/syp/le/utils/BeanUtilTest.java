package com.syp.le.utils;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.syp.le.dto.CustomEventDto;

public class BeanUtilTest {

	@Test
	public void shouldCopyAllProperties() {
		// Given
		CustomEventDto source = new CustomEventDto();
		source.setCity("city");
		source.setCountry("country");
		source.setDescription("description");

		CustomEventDto dest = new CustomEventDto();
		dest.setCity("aaaa");

		// When
		BeanUtil.copyAllProperties(dest, source);

		// Then
		assertThat(dest.getCity()).isEqualTo("city");
		assertThat(dest.getCountry()).isEqualTo("country");
		assertThat(dest.getDescription()).isEqualTo("description");
	}

	@Test
	public void shouldCopyNonnullProperties() {
		// Given
		CustomEventDto source = new CustomEventDto();
		source.setCity("city");
		source.setCountry("country");
		source.setDescription("description");

		CustomEventDto dest = new CustomEventDto();
		dest.setId("id");

		// When
		BeanUtil.copyNonnullProperties(dest, source);

		// Then
		assertThat(dest.getCity()).isEqualTo("city");
		assertThat(dest.getCountry()).isEqualTo("country");
		assertThat(dest.getDescription()).isEqualTo("description");
		assertThat(dest.getId()).isEqualTo("id");
	}
}
