package com.syp.le.controller;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.google.common.collect.Lists;
import com.syp.le.dto.CustomEventDto;
import com.syp.le.mapper.CustomEventMapper;
import com.syp.le.model.CustomEventModel;
import com.syp.le.service.EventService;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(secure = false)
@WebMvcTest(EventController.class)
public class EventControllerTest {

	@MockBean
	private EventService service;
	@MockBean
	private CustomEventMapper mapper;
	@Autowired
	private MockMvc mockMvc;

	public void shouldGetLondonEvents() throws Exception {
		// Given
		given(service.findEventfulCategories()).willReturn(Lists.newArrayList("music"));
		given(service.findEvents(any(Pageable.class), anyString(), anyString(), anyString(), anyString()))
				.willReturn(modelPage());
		given(mapper.toDtoPage(Matchers.<Page<CustomEventModel>>any(), any(Pageable.class))).willReturn(dtoPage());

		// When
		MvcResult mvcResult = mockMvc.perform(get("/api/v1/events/london?page=0&size=30"))
				.andExpect(request().asyncStarted()).andReturn();

		// Then
		mockMvc.perform(asyncDispatch(mvcResult)).andExpect(status().isOk());
	}

	@Test
	public void shouldGetEvent() throws Exception {
		// Given
		given(service.findOneById(anyString())).willReturn(customEventModel());
		given(mapper.toDto(any(CustomEventModel.class))).willReturn(customEventDto());

		// When
		MvcResult mvcResult = mockMvc.perform(get("/api/v1/events/12345")).andExpect(request().asyncStarted())
				.andReturn();

		// Then
		mockMvc.perform(asyncDispatch(mvcResult)).andExpect(status().isOk()).andExpect(jsonPath("$.city").value("city"))
				.andExpect(jsonPath("$.country").value("country"))
				.andExpect(jsonPath("$.description").value("description")).andExpect(jsonPath("$.id").value("id"))
				.andExpect(jsonPath("$.imageUrl").value("imageUrl")).andExpect(jsonPath("$.latitude").value("0.0"))
				.andExpect(jsonPath("$.longitude").value("0.0")).andExpect(jsonPath("$.startTime").value("startTime"))
				.andExpect(jsonPath("$.stopTime").value("stopTime")).andExpect(jsonPath("$.title").value("title"));
	}

	private Page<CustomEventDto> dtoPage() {
		return new PageImpl<CustomEventDto>(Lists.newArrayList(customEventDto()), pageable(), 1);
	}

	private CustomEventDto customEventDto() {
		CustomEventDto dto = new CustomEventDto();
		dto.setCity("city");
		dto.setCountry("country");
		dto.setDescription("description");
		dto.setId("id");
		dto.setImageUrl("imageUrl");
		dto.setLatitude(0f);
		dto.setLongitude(0f);
		dto.setStartTime("startTime");
		dto.setStopTime("stopTime");
		dto.setTitle("title");
		return dto;
	}

	private Page<CustomEventModel> modelPage() {
		return new PageImpl<CustomEventModel>(Lists.newArrayList(customEventModel()), pageable(), 1);
	}

	private CustomEventModel customEventModel() {
		CustomEventModel model = new CustomEventModel();
		model.setCity("city");
		model.setCountry("country");
		model.setDescription("description");
		model.setId("id");
		model.setImageUrl("imageUrl");
		model.setLatitude(0f);
		model.setLongitude(0f);
		model.setStartTime("startTime");
		model.setStopTime("stopTime");
		model.setTitle("title");
		return model;
	}

	private Pageable pageable() {
		return new PageRequest(0, 30);
	}
}
