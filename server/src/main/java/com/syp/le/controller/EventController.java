package com.syp.le.controller;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import javax.annotation.Nonnull;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.syp.le.dto.CustomEventDto;
import com.syp.le.exception.ClientException;
import com.syp.le.mapper.CustomEventMapper;
import com.syp.le.model.CustomEventModel;
import com.syp.le.response.PageResponse;
import com.syp.le.response.SingleResponse;
import com.syp.le.service.EventService;

/**
 * @author <a href="mailto:ricky.shiyouping@gmail.com">Ricky Shi</a>
 *
 * @since 25 Jul 2019
 */
@RestController
@RequestMapping("/api/v1/events")
public class EventController {

	private static final Logger logger = LoggerFactory.getLogger(EventController.class);
	private static final String LOCATION_LONDON = "London";

	private final EventService eventService;
	private final CustomEventMapper eventMapper;

	@Autowired
	public EventController(@Nonnull EventService eventService, @Nonnull CustomEventMapper eventMapper) {
		checkNotNull(eventMapper, "eventMapper cannot be null");
		checkNotNull(eventService, "eventService cannot be null");

		this.eventService = eventService;
		this.eventMapper = eventMapper;
	}

	@GetMapping(path = "/london")
	public PageResponse<CustomEventDto> getLondonEvents(
			@RequestParam(name = "category", required = false) String category, Pageable pageable) {
		logger.info("Client requests london events. category={}, pageable={}", category, pageable);

		return new PageResponse<CustomEventDto>(() -> {
			List<String> categories = eventService.findEventfulCategories();
			if (!StringUtils.isBlank(category) && !categories.contains(category)) {
				throw new ClientException(String.format("Invalid category %s", category), HttpStatus.BAD_REQUEST);
			}

			Page<CustomEventModel> modelPage = eventService.findEvents(pageable, category, null, LOCATION_LONDON, null);
			return eventMapper.toDtoPage(modelPage, pageable);
		});
	}

	@GetMapping(path = "/{id}")
	public SingleResponse<CustomEventDto> getEvent(@PathVariable("id") String id) {
		logger.info("Client requests an event. id={}", id);

		return new SingleResponse<CustomEventDto>(() -> {
			CustomEventModel model = eventService.findOneById(id);
			return eventMapper.toDto(model);
		});
	}
}
