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
import com.syp.le.client.WeatherBitClient;
import com.syp.le.model.CustomEventModel;
import com.syp.le.model.EventCategoryModel;
import com.syp.le.model.EventCategoryModel.CategoryModel;
import com.syp.le.model.EventfulModel;
import com.syp.le.model.EventfulModel.EventModel;
import com.syp.le.model.EventfulModel.EventsModel;
import com.syp.le.model.WeatherBitModel;
import com.syp.le.model.WeatherBitModel.DayModel;
import com.syp.le.model.WeatherBitModel.WeatherModel;

public class EventServiceTest {

	private static EventService service;
	private static EventfulClient eventfulClient;
	private static WeatherBitClient weatherClient;

	@BeforeClass
	public static void setup() {
		weatherClient = mock(WeatherBitClient.class);
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
		given(weatherClient.getWeatherForecast(anyFloat(), anyFloat())).willReturn(weatherBitModel());

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
		model.setStartTime("2019-07-29 18:00:00");
		return model;
	}

	private WeatherBitModel weatherBitModel() {
		WeatherBitModel model = new WeatherBitModel();
		model.setCityName("London");
		model.setCountryCode("UK");
		model.setLatitude(0f);
		model.setLongitude(0f);
		model.setData(dayModelList());
		return model;
	}

	private List<DayModel> dayModelList() {
		WeatherModel weather1 = new WeatherModel();
		weather1.setDescription("Rain");
		DayModel day1 = new DayModel();
		day1.setDate("2019-07-30");
		day1.setWeather(weather1);

		WeatherModel weather2 = new WeatherModel();
		weather2.setDescription("Cloud");
		DayModel day2 = new DayModel();
		day2.setDate("2019-07-31");
		day2.setWeather(weather2);

		return Lists.newArrayList(day1, day2);
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
