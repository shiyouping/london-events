package com.syp.le.client;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import com.syp.le.config.AppConfig;
import com.syp.le.model.CurrentWeatherModel;

/**
 * @author <a href="mailto:ricky.shiyouping@gmail.com">Ricky Shi</a>
 *
 * @since 25 Jul 2019
 */
public class OpenWeatherMapClientImpl implements OpenWeatherMapClient {

	private static final Logger logger = LoggerFactory.getLogger(OpenWeatherMapClientImpl.class);

	private final String apiKey;
	private final RestTemplate restTemplate;
	private final String currentWeatherApiUrl;

	public OpenWeatherMapClientImpl(@Nonnull AppConfig config, @Nonnull RestTemplate restTemplate) {
		checkNotNull(config, "config cannot be null");
		checkNotNull(restTemplate, "restTemplate cannot be null");

		this.restTemplate = restTemplate;
		this.apiKey = config.getOpenWeatherMap().getApiKey();
		this.currentWeatherApiUrl = config.getOpenWeatherMap().getBaseUrl() + "/weather";
	}

	@Override
	public CurrentWeatherModel getCurrentWeather(Float latitude, Float longitude) {
		// TODO Auto-generated method stub
		return null;
	}
}
