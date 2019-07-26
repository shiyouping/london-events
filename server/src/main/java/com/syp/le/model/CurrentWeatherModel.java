package com.syp.le.model;

import java.util.List;

import org.pojomatic.annotations.AutoProperty;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author <a href="mailto:ricky.shiyouping@gmail.com">Ricky Shi</a>
 *
 * @since 26 Jul 2019
 */
@AutoProperty
public class CurrentWeatherModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	@JSONField(name = "coord")
	private CoordinateModel coordinate;
	@JSONField(name = "weather")
	private List<WeatherModel> weather;
	@JSONField(name = "dt")
	private Long time;
	@JSONField(name = "timezone")
	private Integer timezone;
	@JSONField(name = "name")
	private String name;

	public CoordinateModel getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(CoordinateModel coordinate) {
		this.coordinate = coordinate;
	}

	public List<WeatherModel> getWeather() {
		return weather;
	}

	public void setWeather(List<WeatherModel> weather) {
		this.weather = weather;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public Integer getTimezone() {
		return timezone;
	}

	public void setTimezone(Integer timezone) {
		this.timezone = timezone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@AutoProperty
	public static class CoordinateModel extends BaseModel {
		private static final long serialVersionUID = 1L;

		@JSONField(name = "lat")
		private Float latitude;
		@JSONField(name = "lon")
		private Float longitude;

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
	}

	@AutoProperty
	public static class WeatherModel extends BaseModel {
		private static final long serialVersionUID = 1L;

		@JSONField(name = "main")
		private String main;
		@JSONField(name = "description")
		private String description;
		@JSONField(name = "icon")
		private String icon;

		public String getMain() {
			return main;
		}

		public void setMain(String main) {
			this.main = main;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getIcon() {
			return icon;
		}

		public void setIcon(String icon) {
			this.icon = icon;
		}
	}
}
