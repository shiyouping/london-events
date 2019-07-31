package com.syp.le.client;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.alibaba.fastjson.JSON;
import com.syp.le.config.AppConfig;
import com.syp.le.model.EventCategoryModel;
import com.syp.le.model.EventfulModel;
import com.syp.le.model.EventfulModel.EventModel;
import com.syp.le.utils.EventfulUtil;
import com.syp.le.utils.MapUtil;
import com.syp.le.utils.StringUtil;

/**
 * @author <a href="mailto:ricky.shiyouping@gmail.com">Ricky Shi</a>
 *
 * @since 25 Jul 2019
 */
@Component
public class EventfulClientImpl implements EventfulClient {

	private static final Logger logger = LoggerFactory.getLogger(EventfulClientImpl.class);
	private static final String QUERY_PARAM_ID = "id";
	private static final String QUERY_PARAM_APP_KEY = "app_key";
	private static final String QUERY_PARAM_CHANGE_MULTI_DAY_START = "change_multi_day_start";
	private static final String QUERY_PARAM_INCLUDE = "include";
	private static final String QUERY_PARAM_MATURE = "mature";
	private static final String QUERY_PARAM_LANGUAGES = "languages";
	private static final String QUERY_PARAM_IMAGE_SIZES = "image_sizes";
	private static final String QUERY_PARAM_PAGE_NUMBER = "page_number";
	private static final String QUERY_PARAM_PAGE_SIZE = "page_size";
	private static final String QUERY_PARAM_SORT_DIRECTION = "sort_direction";
	private static final String QUERY_PARAM_SORT_ORDER = "sort_order";
	private static final String QUERY_PARAM_COUNT_ONLY = "count_only";
	private static final String QUERY_PARAM_UNITS = "units";
	private static final String QUERY_PARAM_WITHIN = "within";
	private static final String QUERY_PARAM_EXCLUDED_CATEGORY = "ex_category";
	private static final String QUERY_PARAM_CATEGORY = "category";
	private static final String QUERY_PARAM_DATE = "date";
	private static final String QUERY_PARAM_LOCATION = "location";
	private static final String QUERY_PARAM_KEYWORDS = "keywords";

	private final String apiKey;
	private final String eventSearchApiUrl;
	private final String eventCategoryApiUrl;
	private final String eventGetApiUrl;
	private final RestTemplate restTemplate;

	@Autowired
	public EventfulClientImpl(@Nonnull AppConfig config, @Nonnull RestTemplate restTemplate) {
		checkNotNull(config, "config cannot be null");
		checkNotNull(restTemplate, "restTemplate cannot be null");

		this.restTemplate = restTemplate;
		this.apiKey = config.getEventful().getApiKey();
		this.eventSearchApiUrl = config.getEventful().getBaseUrl() + "/events/search";
		this.eventCategoryApiUrl = config.getEventful().getBaseUrl() + "/categories/list";
		this.eventGetApiUrl = config.getEventful().getBaseUrl() + "/events/get";
	}

	@Override
	public EventfulModel searchEvents(String keywords, String location, String date, String category,
			String excludedCategory, Integer within, String units, Boolean countryOnly, String sortOrder,
			String sortDirection, Integer pageSize, Integer pageNumber, String imageSizes, Integer languages,
			String mature, String include, Boolean changeMultiDayStart) {

		MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
		MapUtil.add(queryParams, QUERY_PARAM_KEYWORDS, keywords);
		MapUtil.add(queryParams, QUERY_PARAM_LOCATION, location);
		MapUtil.add(queryParams, QUERY_PARAM_DATE, date);
		MapUtil.add(queryParams, QUERY_PARAM_CATEGORY, category);
		MapUtil.add(queryParams, QUERY_PARAM_EXCLUDED_CATEGORY, excludedCategory);
		MapUtil.add(queryParams, QUERY_PARAM_WITHIN, StringUtil.toString(within));
		MapUtil.add(queryParams, QUERY_PARAM_UNITS, units);
		MapUtil.add(queryParams, QUERY_PARAM_COUNT_ONLY, StringUtil.toString(countryOnly));
		MapUtil.add(queryParams, QUERY_PARAM_SORT_ORDER, sortOrder);
		MapUtil.add(queryParams, QUERY_PARAM_SORT_DIRECTION, sortDirection);
		MapUtil.add(queryParams, QUERY_PARAM_PAGE_SIZE, StringUtil.toString(pageSize));
		MapUtil.add(queryParams, QUERY_PARAM_PAGE_NUMBER, StringUtil.toString(pageNumber));
		MapUtil.add(queryParams, QUERY_PARAM_IMAGE_SIZES, imageSizes);
		MapUtil.add(queryParams, QUERY_PARAM_LANGUAGES, StringUtil.toString(languages));
		MapUtil.add(queryParams, QUERY_PARAM_MATURE, mature);
		MapUtil.add(queryParams, QUERY_PARAM_INCLUDE, include);
		MapUtil.add(queryParams, QUERY_PARAM_CHANGE_MULTI_DAY_START, StringUtil.toString(changeMultiDayStart));
		MapUtil.add(queryParams, QUERY_PARAM_APP_KEY, apiKey);

		String uri = UriComponentsBuilder.fromUriString(eventSearchApiUrl).queryParams(queryParams).toUriString();
		logger.info("Searching events uri={}", uri);

		String response = null;
		try {
			response = restTemplate.getForObject(uri, String.class);
			EventfulUtil.checkErrorResponse(response);
			return JSON.parseObject(response, EventfulModel.class);
		} catch (Exception e) {
			logger.error(String.format("Failed to request or parse events data. Response=%s", response), e);
			return null;
		}
	}

	@Override
	public EventCategoryModel getCategoryList() {
		MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
		MapUtil.add(queryParams, QUERY_PARAM_APP_KEY, apiKey);

		String uri = UriComponentsBuilder.fromUriString(eventCategoryApiUrl).queryParams(queryParams).toUriString();
		logger.info("Getting event categories uri={}", uri);

		String response = null;
		try {
			response = restTemplate.getForObject(uri, String.class);
			EventfulUtil.checkErrorResponse(response);

			// Only useful data will be mapped to Java object
			// Useless information will be discarded when parsing
			return JSON.parseObject(response, EventCategoryModel.class);
		} catch (Exception e) {
			logger.error(String.format("Failed to request or parse category data. Response=%s", response), e);
			return null;
		}
	}

	@Override
	public EventModel getAnEvent(String id) {
		checkNotNull(id, "id cannot be null");

		MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
		MapUtil.add(queryParams, QUERY_PARAM_APP_KEY, apiKey);
		MapUtil.add(queryParams, QUERY_PARAM_ID, id);

		String uri = UriComponentsBuilder.fromUriString(eventGetApiUrl).queryParams(queryParams).toUriString();
		logger.info("Getting an event uri={}", uri);

		String response = null;
		try {
			response = restTemplate.getForObject(uri, String.class);
			EventfulUtil.checkErrorResponse(response);

			// Only useful data will be mapped to Java object
			// Useless information will be discarded when parsing
			return JSON.parseObject(response, EventModel.class);
		} catch (Exception e) {
			logger.error(String.format("Failed to request or parse event data. Response=%s", response), e);
			return null;
		}
	}
}
