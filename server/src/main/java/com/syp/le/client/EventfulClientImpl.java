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

	private final String apiKey;
	private final String eventSearchApiUrl;
	private final String eventCategoryApiUrl;
	private final RestTemplate restTemplate;

	@Autowired
	public EventfulClientImpl(@Nonnull AppConfig config, @Nonnull RestTemplate restTemplate) {
		checkNotNull(config, "config cannot be null");
		checkNotNull(restTemplate, "restTemplate cannot be null");

		this.restTemplate = restTemplate;
		this.apiKey = config.getEventful().getApiKey();
		this.eventSearchApiUrl = config.getEventful().getBaseUrl() + "/events/search";
		this.eventCategoryApiUrl = config.getEventful().getBaseUrl() + "/categories/list";
	}

	@Override
	public EventfulModel searchEvents(String keywords, String location, String date, String category,
			String excludedCategory, Integer within, String units, Boolean countryOnly, String sortOrder,
			String sortDirection, Integer pageSize, Integer pageNumber, String imageSizes, Integer languages,
			String mature, String include, Boolean changeMultiDayStart) {

		MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
		MapUtil.add(queryParams, "keywords", keywords);
		MapUtil.add(queryParams, "location", location);
		MapUtil.add(queryParams, "date", date);
		MapUtil.add(queryParams, "category", category);
		MapUtil.add(queryParams, "ex_category", excludedCategory);
		MapUtil.add(queryParams, "within", StringUtil.toString(within));
		MapUtil.add(queryParams, "units", units);
		MapUtil.add(queryParams, "count_only", StringUtil.toString(countryOnly));
		MapUtil.add(queryParams, "sort_order", sortOrder);
		MapUtil.add(queryParams, "sort_direction", sortDirection);
		MapUtil.add(queryParams, "page_size", StringUtil.toString(pageSize));
		MapUtil.add(queryParams, "page_number", StringUtil.toString(pageNumber));
		MapUtil.add(queryParams, "image_sizes", imageSizes);
		MapUtil.add(queryParams, "languages", StringUtil.toString(languages));
		MapUtil.add(queryParams, "mature", mature);
		MapUtil.add(queryParams, "include", include);
		MapUtil.add(queryParams, "change_multi_day_start ", StringUtil.toString(changeMultiDayStart));
		MapUtil.add(queryParams, "app_key", apiKey);

		String uri = UriComponentsBuilder.fromUriString(eventSearchApiUrl).queryParams(queryParams).toUriString();
		logger.info("Searching events uri={}", uri);

		String response = restTemplate.getForObject(uri, String.class);
		return JSON.parseObject(response, EventfulModel.class);
	}

	@Override
	public EventCategoryModel getCategoryList() {
		MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
		MapUtil.add(queryParams, "app_key", apiKey);

		String uri = UriComponentsBuilder.fromUriString(eventCategoryApiUrl).queryParams(queryParams).toUriString();
		logger.info("Getting event categories uri={}", uri);

		String response = restTemplate.getForObject(uri, String.class);
		return JSON.parseObject(response, EventCategoryModel.class);
	}
}
