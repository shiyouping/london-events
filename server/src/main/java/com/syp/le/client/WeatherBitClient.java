package com.syp.le.client;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.syp.le.model.WeatherBitModel;

/**
 * @author <a href="mailto:ricky.shiyouping@gmail.com">Ricky Shi</a>
 *
 * @since 29 Jul 2019
 */
public interface WeatherBitClient {

	/**
	 * Get the current weather data by geographic coordinates <br>
	 * 
	 * See detailed document at
	 * https://www.weatherbit.io/api/weather-forecast-16-day
	 */
	@Nullable
	WeatherBitModel getWeatherForecast(@Nonnull Float latitude, @Nonnull Float longitude);
}
