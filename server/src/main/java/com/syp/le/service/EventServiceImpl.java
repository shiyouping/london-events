package com.syp.le.service;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;
import com.syp.le.client.EventfulClient;
import com.syp.le.client.OpenWeatherMapClient;
import com.syp.le.model.CurrentWeatherModel;
import com.syp.le.model.CustomEventModel;
import com.syp.le.model.EventCategoryModel;
import com.syp.le.model.EventfulModel;
import com.syp.le.model.EventfulModel.EventModel;
import com.syp.le.model.EventfulModel.ImageModel;
import com.syp.le.utils.BeanUtil;
import com.syp.le.utils.EventfulUtil;

/**
 * @author <a href="mailto:ricky.shiyouping@gmail.com">Ricky Shi</a>
 *
 * @since 25 Jul 2019
 */
@Service
@CacheConfig(cacheNames = "events")
public class EventServiceImpl implements EventService {

	private static final String UNKNOWN = "Unknown";
	private static final Logger logger = LoggerFactory.getLogger(EventServiceImpl.class);

	private final EventfulClient eventfulClient;
	private final OpenWeatherMapClient weatherClient;

	@Autowired
	public EventServiceImpl(@Nonnull OpenWeatherMapClient weatherClient, @Nonnull EventfulClient eventfulClient) {
		checkNotNull(weatherClient, "weatherClient cannot be null");
		checkNotNull(eventfulClient, "eventfulClient cannot be null");

		this.weatherClient = weatherClient;
		this.eventfulClient = eventfulClient;
	}

	@Override
	@Cacheable
	public List<String> findEventfulCategories() {
		logger.info("Finding eventful categories...");

		EventCategoryModel categoryModel = eventfulClient.getCategoryList();
		if (categoryModel == null || CollectionUtils.isEmpty(categoryModel.getCategory())) {
			return Lists.newArrayList();
		}

		return categoryModel.getCategory().parallelStream().map(model -> model.getId()).collect(Collectors.toList());
	}

	@Override
	public Page<CustomEventModel> findEvents(Pageable pageable, String category, String keywords, String location,
			String date) {
		checkNotNull(pageable, "pageable cannot be null");
		checkArgument(
				!(StringUtils.isBlank(category) && StringUtils.isBlank(keywords) && StringUtils.isBlank(location)
						&& StringUtils.isBlank(date)),
				"Either the 'keywords', 'location', 'category' or 'date' parameter is required.");

		logger.info("Finding london events... Pageable={}, category={}, keywords={}, location={}, date={}", pageable,
				category, keywords, location, date);

		EventfulModel eventfulModel = eventfulClient.searchEvents(keywords, location, date, category, null, null, null,
				null, EventfulUtil.getEventSortOrder(pageable), EventfulUtil.getEventSortDirection(pageable),
				pageable.getPageSize(), pageable.getPageNumber() + 1, null, null, null, null, null);
		return toPage(eventfulModel, pageable);
	}

	@Override
	public CustomEventModel findOneById(String id) {
		checkNotNull(id, "id cannot be null");
		logger.info("Finding an event by id={}...", id);

		EventModel eventModel = eventfulClient.getAnEvent(id);
		if (eventModel == null) {
			return null;
		}

		return toCustomEventModel(eventModel);
	}

	private Page<CustomEventModel> toPage(EventfulModel eventfulModel, Pageable pageable) {
		if (eventfulModel == null || eventfulModel.getEvents() == null) {
			return new PageImpl<>(Lists.newArrayList(), pageable, 0);
		}

		List<EventModel> eventModels = eventfulModel.getEvents().getEvent();
		return new PageImpl<>(toCustomEventModels(eventModels), pageable, eventfulModel.getTotalItems());
	}

	private List<CustomEventModel> toCustomEventModels(List<EventModel> eventModels) {
		if (CollectionUtils.isEmpty(eventModels)) {
			return Lists.newArrayList();
		}

		return eventModels.parallelStream().map(eventModel -> toCustomEventModel(eventModel))
				.collect(Collectors.toList());
	}

	private CustomEventModel toCustomEventModel(EventModel eventModel) {
		CustomEventModel customEventModel = new CustomEventModel();
		BeanUtil.copyAllProperties(customEventModel, eventModel);
		customEventModel.setCity(eventModel.getCityName());
		customEventModel.setCountry(eventModel.getCountryName());
		customEventModel.setImageUrl(getImageUrl(eventModel.getImage()));
		customEventModel.setWeather(getWeather(eventModel));
		return customEventModel;
	}

	private String getImageUrl(ImageModel imageModel) {
		if (imageModel == null || imageModel.getMedium() == null) {
			return null;
		}

		return imageModel.getMedium().getUrl();
	}

	private String getWeather(EventModel eventModel) {
		if (eventModel == null || eventModel.getLatitude() == null || eventModel.getLongitude() == null) {
			return UNKNOWN;
		}

		CurrentWeatherModel weatherModel = weatherClient.getCurrentWeather(eventModel.getLatitude(),
				eventModel.getLongitude());

		if (CollectionUtils.isEmpty(weatherModel.getWeather())) {
			return UNKNOWN;
		}

		String description = weatherModel.getWeather().get(0).getMain();
		if (StringUtils.isBlank(description)) {
			return UNKNOWN;
		}

		return description;
	}
}
