package com.syp.le.service;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;
import com.syp.le.client.EventfulClient;
import com.syp.le.client.WeatherBitClient;
import com.syp.le.model.CustomEventModel;
import com.syp.le.model.EventCategoryModel;
import com.syp.le.model.EventfulModel;
import com.syp.le.model.EventfulModel.EventModel;
import com.syp.le.model.EventfulModel.ImageModel;
import com.syp.le.model.WeatherBitModel;
import com.syp.le.model.WeatherBitModel.DayModel;
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

	private static final Logger logger = LoggerFactory.getLogger(EventServiceImpl.class);
	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private static final String UNKNOWN = "Unknown";

	private final EventfulClient eventfulClient;
	private final WeatherBitClient weatherClient;

	@Autowired
	public EventServiceImpl(@Nonnull WeatherBitClient weatherClient, @Nonnull EventfulClient eventfulClient) {
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
	@Cacheable
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
	@Cacheable
	public CustomEventModel findOneById(String id) {
		checkNotNull(id, "id cannot be null");
		logger.info("Finding an event by id={}...", id);

		EventModel eventModel = eventfulClient.getAnEvent(id);
		if (eventModel == null) {
			return null;
		}

		return toCustomEventModel(eventModel);
	}

	@Override
	@CacheEvict(allEntries = true)
	public void clearEventCache() {
		logger.info("****** Clearing Event Cache ******");
	}

	private Page<CustomEventModel> toPage(EventfulModel eventfulModel, Pageable pageable) {
		if (eventfulModel == null || eventfulModel.getEvents() == null) {
			return new PageImpl<>(Lists.newArrayList(), pageable, 0);
		}

		List<EventModel> eventModels = eventfulModel.getEvents().getEvent();

		// The total items returned from Eventful API is not accurate
		// See details at https://api.eventful.com/docs/events/search
		// So we have to calculate the approximate total by ourself
		return new PageImpl<>(toCustomEventModels(eventModels), pageable,
				eventfulModel.getPageCount() * eventfulModel.getPageSize());
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

		WeatherBitModel weatherForecast = weatherClient.getWeatherForecast(eventModel.getLatitude(),
				eventModel.getLongitude());

		if (weatherForecast == null) {
			return UNKNOWN;
		}

		return getClosestWeather(eventModel.getStartTime(), weatherForecast.getData());
	}

	/**
	 * 
	 * There is a list of {@link DayModel}s with dates: 2019-07-29, 2019-07-30,
	 * 2019-07-31 <br>
	 * 
	 * 1. If eventTime = 2019-07-26 18:00, then {@link DayModel} with date
	 * 2019-07-29 will be matched <br>
	 * 
	 * 2. If eventTime = 2019-07-29 18:00, then {@link DayModel} with date
	 * 2019-07-29 will be matched <br>
	 * 
	 * 3. If eventTime = 2019-07-31 18:00, then {@link DayModel} with date
	 * 2019-07-31 will be matched <br>
	 * 
	 * 4. If eventTime = 2019-08-03 18:00, then {@link DayModel} with date
	 * 2019-07-31 will be matched <br>
	 * 
	 */
	private String getClosestWeather(String eventTime, List<DayModel> futureWeatherData) {
		if (StringUtils.isBlank(eventTime) || CollectionUtils.isEmpty(futureWeatherData)) {
			return UNKNOWN;
		}

		DayModel closestDay = null;
		long min = Long.MAX_VALUE;
		LocalDateTime start = LocalDateTime.parse(eventTime, DATE_TIME_FORMATTER);

		for (DayModel day : futureWeatherData) {
			LocalDateTime end = LocalDate.parse(day.getDate(), DATE_FORMATTER).atStartOfDay();
			long difference = Math.abs(ChronoUnit.DAYS.between(start, end));

			if (difference < min) {
				min = difference;
				closestDay = day;
			}
		}

		if (closestDay.getWeather() == null || StringUtils.isBlank(closestDay.getWeather().getDescription())) {
			return UNKNOWN;
		}

		return closestDay.getWeather().getDescription();
	}
}
