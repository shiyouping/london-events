package com.syp.le.config;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = PropertiesConfig.class)
@TestPropertySource(properties = { "app.eventful.apiKey=test", "app.eventful.baseUrl=test",
		"app.openWeatherMap.apiKey=test", "app.openWeatherMap.baseUrl=test", "app.weatherbit.apiKey=test",
		"app.weatherbit.baseUrl=test" })
public class AppConfigTest {
	@Autowired
	private AppConfig appConfig;

	@Test
	public void shouldGetProperties() {
		assertThat(appConfig.getEventful().getApiKey()).isEqualTo("test");
		assertThat(appConfig.getEventful().getBaseUrl()).isEqualTo("test");
		assertThat(appConfig.getOpenWeatherMap().getApiKey()).isEqualTo("test");
		assertThat(appConfig.getOpenWeatherMap().getBaseUrl()).isEqualTo("test");
	}
}
