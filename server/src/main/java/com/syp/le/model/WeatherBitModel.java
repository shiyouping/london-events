package com.syp.le.model;

import java.util.List;

import org.pojomatic.annotations.AutoProperty;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author <a href="mailto:ricky.shiyouping@gmail.com">Ricky Shi</a>
 *
 * @since 29 Jul 2019
 */
@AutoProperty
public class WeatherBitModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	@JSONField(name = "city_name")
	private String cityName;
	@JSONField(name = "country_code")
	private String countryCode;
	@JSONField(name = "lat")
	private Float latitude;
	@JSONField(name = "lon")
	private Float longitude;
	@JSONField(name = "data")
	private List<DayModel> data;

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public Float getLatitude() {
		return latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	public Float getLongitude() {
		return longitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	public List<DayModel> getData() {
		return data;
	}

	public void setData(List<DayModel> data) {
		this.data = data;
	}

	@AutoProperty
	public static class DayModel extends BaseModel {

		private static final long serialVersionUID = 1L;
		@JSONField(name = "valid_date")
		private String date;
		@JSONField(name = "temp")
		private Float temperature;
		private WeatherModel weather;

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

		public Float getTemperature() {
			return temperature;
		}

		public void setTemperature(Float temperature) {
			this.temperature = temperature;
		}

		public WeatherModel getWeather() {
			return weather;
		}

		public void setWeather(WeatherModel weather) {
			this.weather = weather;
		}
	}

	@AutoProperty
	public static class WeatherModel extends BaseModel {

		private static final long serialVersionUID = 1L;
		private String description;

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}
	}
}
