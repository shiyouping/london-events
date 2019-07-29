package com.syp.le.client;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.syp.le.model.OpenWeatherModel;

/**
 * @author <a href="mailto:ricky.shiyouping@gmail.com">Ricky Shi</a>
 *
 * @since 25 Jul 2019
 */
public interface OpenWeatherMapClient {

	/**
	 * Get the current weather data by geographic coordinates <br>
	 * 
	 * See detailed document at https://openweathermap.org/current
	 */
	@Nullable
	OpenWeatherModel getCurrentWeather(@Nonnull Float latitude, @Nonnull Float longitude);

	/*****************************************************************************
	 * OpenWeatherMap doesn't provide free API of future weather for personal use.
	 * So this application doesn't provide API to query weather forecast
	 *****************************************************************************
	 */
}
