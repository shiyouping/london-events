package com.syp.le.utils;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;

import com.syp.le.exception.ApplicationException;

public class EventfulUtilTest {

	@Test(expected = ApplicationException.class)
	public void shouldCheckErrorResponse() {
		// Given
		String response = "{\"status\":\"Not found\",\"error\":\"1\",\"description\":\"There is no event with the identifier 'E0-001-12636913-9f'.\"}";

		// When
		EventfulUtil.checkErrorResponse(response);

		// Then the exception should be caught by JUnit
	}

	@Test
	public void shouldGetEventSortDirection() {
		// Given
		Pageable pageable = new PageRequest(0, 10, Direction.ASC, "date");

		// When
		String direction = EventfulUtil.getEventSortDirection(pageable);

		// Then
		assertThat(direction).isEqualTo("ascending");
	}

	@Test
	public void shouldGetEventSortOrder() {
		// Given
		Pageable pageable = new PageRequest(0, 10, Direction.ASC, "date");

		// When
		String order = EventfulUtil.getEventSortOrder(pageable);

		// Then
		assertThat(order).isEqualTo("date");
	}
}
