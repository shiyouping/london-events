package com.syp.le.config;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

/**
 * @author <a href="mailto:ricky.shiyouping@gmail.com">Ricky Shi</a>
 *
 * @since 25 Jul 2019
 */
@Validated
@ConfigurationProperties("app")
public class AppConfig {

	@Valid
	private Eventful eventful = new Eventful();

	@Valid
	private OpenWeatherMap openWeatherMap = new OpenWeatherMap();

	@Valid
	private TaskExecutor taskExecutor = new TaskExecutor();

	public Eventful getEventful() {
		return eventful;
	}

	public void setEventful(Eventful eventful) {
		this.eventful = eventful;
	}

	public OpenWeatherMap getOpenWeatherMap() {
		return openWeatherMap;
	}

	public void setOpenWeatherMap(OpenWeatherMap openWeatherMap) {
		this.openWeatherMap = openWeatherMap;
	}

	public TaskExecutor getTaskExecutor() {
		return taskExecutor;
	}

	public void setTaskExecutor(TaskExecutor taskExecutor) {
		this.taskExecutor = taskExecutor;
	}

	public static class Eventful {
		@NotEmpty
		private String apiKey;
		@NotEmpty
		private String baseUrl;

		public String getApiKey() {
			return apiKey;
		}

		public void setApiKey(String apiKey) {
			this.apiKey = apiKey;
		}

		public String getBaseUrl() {
			return baseUrl;
		}

		public void setBaseUrl(String baseUrl) {
			this.baseUrl = baseUrl;
		}
	}

	public static class OpenWeatherMap {
		@NotEmpty
		private String apiKey;
		@NotEmpty
		private String baseUrl;

		public String getApiKey() {
			return apiKey;
		}

		public void setApiKey(String apiKey) {
			this.apiKey = apiKey;
		}

		public String getBaseUrl() {
			return baseUrl;
		}

		public void setBaseUrl(String baseUrl) {
			this.baseUrl = baseUrl;
		}
	}

	public static class TaskExecutor {
		@Min(10)
		private int corePoolSize = 50;
		@Min(50)
		private int maxPoolSize = 200;
		@Min(50)
		private int queueCapacity = 500;

		public int getCorePoolSize() {
			return corePoolSize;
		}

		public int getMaxPoolSize() {
			return maxPoolSize;
		}

		public int getQueueCapacity() {
			return queueCapacity;
		}

		public void setCorePoolSize(int corePoolSize) {
			this.corePoolSize = corePoolSize;
		}

		public void setMaxPoolSize(int maxPoolSize) {
			this.maxPoolSize = maxPoolSize;
		}

		public void setQueueCapacity(int queueCapacity) {
			this.queueCapacity = queueCapacity;
		}
	}
}
