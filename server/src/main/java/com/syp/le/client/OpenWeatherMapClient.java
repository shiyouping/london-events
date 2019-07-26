package com.syp.le.client;

import javax.annotation.Nonnull;

import com.syp.le.model.CurrentWeatherModel;

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
	CurrentWeatherModel getCurrentWeather(@Nonnull Float latitude, @Nonnull Float longitude);
}
