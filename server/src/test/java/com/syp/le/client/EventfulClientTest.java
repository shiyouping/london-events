package com.syp.le.client;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.syp.le.model.EventCategoryModel;
import com.syp.le.model.EventfulModel;
import com.syp.le.model.EventfulModel.EventModel;

@RunWith(SpringRunner.class)
@AutoConfigureWireMock(port = 8080)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = { "app.eventful.baseUrl=http://localhost:8080", "app.eventful.apiKey=test",
		"app.openWeatherMap.baseUrl=http://localhost:8080", "app.openWeatherMap.apiKey=test" })
public class EventfulClientTest extends WiremockBase {

	@Autowired
	private EventfulClient client;

	@Test
	public void shouldGetCategoryList() {
		// Given
		createWireMockStub("/categories/list", "wiremock-event-category.json");

		// When
		EventCategoryModel eventCategoryModel = client.getCategoryList();

		// Then
		assertThat(eventCategoryModel).isNotNull();
		assertThat(eventCategoryModel.getCategory()).isNotNull();
		assertThat(eventCategoryModel.getCategory().size()).isEqualTo(29);
	}

	@Test
	public void shouldSearchEvents() {
		// Given
		createWireMockStub("/events/search", "wiremock-event-search.json");

		// When
		EventfulModel eventfulModel = client.searchEvents(null, "London", null, null, null, null, null, null, null,
				null, null, null, null, null, null, null, null);

		// Then
		assertThat(eventfulModel).isNotNull();
		assertThat(eventfulModel.getTotalItems()).isEqualTo(9479);
		assertThat(eventfulModel.getPageNumber()).isEqualTo(1);
		assertThat(eventfulModel.getPageSize()).isEqualTo(10);
		assertThat(eventfulModel.getPageCount()).isEqualTo(948);
		assertThat(eventfulModel.getSearchTime()).isEqualTo(0.208f);
		assertThat(eventfulModel.getEvents()).isNotNull();
		assertThat(eventfulModel.getEvents().getEvent()).isNotNull();
		assertThat(eventfulModel.getEvents().getEvent().size()).isEqualTo(10);
	}

	@Test
	public void shouldGetAnEvent() {
		// Given
		createWireMockStub("/events/get", "wiremock-event-get.json");

		// When
		EventModel eventModel = client.getAnEvent("E0-001-126369173-9");

		// Then
		assertThat(eventModel).isNotNull();
		assertThat(eventModel.getId()).isEqualTo("E0-001-126369173-9");
		assertThat(eventModel.getGeocodeType()).isEqualTo("EVDB Geocoder");
		assertThat(eventModel.getTitle()).isEqualTo("London Jerk Festival");
	}
}
