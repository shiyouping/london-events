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
import com.syp.le.model.OpenWeatherModel;
import com.syp.le.utils.MapUtil;

/**
 * @author <a href="mailto:ricky.shiyouping@gmail.com">Ricky Shi</a>
 *
 * @since 25 Jul 2019
 */
@Component
public class OpenWeatherMapClientImpl implements OpenWeatherMapClient {

	private static final Logger logger = LoggerFactory.getLogger(OpenWeatherMapClientImpl.class);
	private static final String QUERY_PARAM_APP_ID = "APPID";
	private static final String QUERY_PARAM_LONGITUDE = "lon";
	private static final String QUERY_PARAM_LATITUDE = "lat";

	private final String apiKey;
	private final RestTemplate restTemplate;
	private final String currentWeatherApiUrl;

	@Autowired
	public OpenWeatherMapClientImpl(@Nonnull AppConfig config, @Nonnull RestTemplate restTemplate) {
		checkNotNull(config, "config cannot be null");
		checkNotNull(restTemplate, "restTemplate cannot be null");

		this.restTemplate = restTemplate;
		this.apiKey = config.getOpenWeatherMap().getApiKey();
		this.currentWeatherApiUrl = config.getOpenWeatherMap().getBaseUrl() + "/weather";
	}

	@Override
	public OpenWeatherModel getCurrentWeather(Float latitude, Float longitude) {
		checkNotNull(latitude, "latitude cannot be null");
		checkNotNull(longitude, "longitude cannot be null");

		MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
		MapUtil.add(queryParams, QUERY_PARAM_LATITUDE, latitude.toString());
		MapUtil.add(queryParams, QUERY_PARAM_LONGITUDE, longitude.toString());
		MapUtil.add(queryParams, QUERY_PARAM_APP_ID, apiKey);

		String uri = UriComponentsBuilder.fromUriString(currentWeatherApiUrl).queryParams(queryParams).toUriString();
		logger.info("Getting current weather data uri={}", uri);

		try {
			String response = restTemplate.getForObject(uri, String.class);

			// Only useful data will be mapped to Java object
			// Useless information will be discarded when parsing
			return JSON.parseObject(response, OpenWeatherModel.class);
		} catch (Exception e) {
			logger.error("Failed to request or parse current weather", e);
			return null;
		}
	}
}
