package com.syp.le.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyFloat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.google.common.collect.Lists;
import com.syp.le.client.EventfulClient;
import com.syp.le.client.OpenWeatherMapClient;
import com.syp.le.model.OpenWeatherModel;
import com.syp.le.model.OpenWeatherModel.WeatherModel;
import com.syp.le.model.CustomEventModel;
import com.syp.le.model.EventCategoryModel;
import com.syp.le.model.EventCategoryModel.CategoryModel;
import com.syp.le.model.EventfulModel;
import com.syp.le.model.EventfulModel.EventModel;
import com.syp.le.model.EventfulModel.EventsModel;

public class EventServiceTest {

	private static EventService service;
	private static EventfulClient eventfulClient;
	private static OpenWeatherMapClient weatherClient;

	@BeforeClass
	public static void setup() {
		weatherClient = mock(OpenWeatherMapClient.class);
		eventfulClient = mock(EventfulClient.class);
		service = new EventServiceImpl(weatherClient, eventfulClient);
	}

	@Test
	public void shouldFindEvents() {
		// Given
		Pageable pageable = new PageRequest(0, 30);
		given(eventfulClient.searchEvents(anyString(), anyString(), anyString(), anyString(), anyString(), anyInt(),
				anyString(), anyBoolean(), anyString(), anyString(), anyInt(), anyInt(), anyString(), anyInt(),
				anyString(), anyString(), anyBoolean())).willReturn(eventfulModel());
		given(weatherClient.getCurrentWeather(anyFloat(), anyFloat())).willReturn(currentWeather());

		// When
		Page<CustomEventModel> page = service.findEvents(pageable, null, null, "London", null);

		// Then
		assertThat(page).isNotNull();
		assertThat(page.getNumber()).isEqualTo(0);
		assertThat(page.getNumberOfElements()).isEqualTo(1);
		assertThat(page.getSize()).isEqualTo(30);
		assertThat(page.getTotalPages()).isEqualTo(1);
		assertThat(page.getContent()).isNotNull();
		assertThat(page.getContent().get(0).getCity()).isEqualTo("London");
		assertThat(page.getContent().get(0).getWeather()).isEqualTo("Rain");
	}

	@Test
	public void shouldFindOneById() {
		// Given
		given(eventfulClient.getAnEvent(anyString())).willReturn(eventModel());

		// When
		CustomEventModel model = service.findOneById("id");

		// Then
		assertThat(model).isNotNull();
		assertThat(model.getCity()).isEqualTo("London");
		assertThat(model.getId()).isEqualTo("id");
		assertThat(model.getTitle()).isEqualTo("title");
		assertThat(model.getLatitude()).isEqualTo(0f);
		assertThat(model.getLongitude()).isEqualTo(0f);
	}

	@Test
	public void shouldFindEventfulCategories() {
		// Given
		given(eventfulClient.getCategoryList()).willReturn(eventCategoryModel());

		// When
		List<String> categories = service.findEventfulCategories();

		// Then
		assertThat(categories).isNotNull();
		assertThat(categories.size()).isEqualTo(1);
		assertThat(categories.get(0)).isEqualTo("music");
	}

	private EventfulModel eventfulModel() {
		EventfulModel model = new EventfulModel();
		model.setPageCount(1);
		model.setPageNumber(0);
		model.setPageSize(30);
		model.setTotalItems(1);
		model.setEvents(eventsModel());
		return model;
	}

	private EventsModel eventsModel() {
		EventsModel model = new EventsModel();
		model.setEvent(Lists.newArrayList(eventModel()));
		return model;
	}

	private EventModel eventModel() {
		EventModel model = new EventModel();
		model.setId("id");
		model.setCityName("London");
		model.setTitle("title");
		model.setLatitude(0f);
		model.setLongitude(0f);
		return model;
	}

	private OpenWeatherModel currentWeather() {
		OpenWeatherModel model = new OpenWeatherModel();
		model.setName("London");
		model.setWeather(Lists.newArrayList(weather()));
		return model;
	}

	private WeatherModel weather() {
		WeatherModel model = new WeatherModel();
		model.setMain("Rain");
		return model;
	}

	private EventCategoryModel eventCategoryModel() {
		EventCategoryModel model = new EventCategoryModel();
		model.setCategory(categoryModels());
		return model;
	}

	private List<CategoryModel> categoryModels() {
		CategoryModel model = new CategoryModel();
		model.setId("music");
		return Lists.newArrayList(model);
	}
}
