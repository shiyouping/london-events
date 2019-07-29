package com.syp.le.client;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.syp.le.model.WeatherBitModel;

@RunWith(SpringRunner.class)
@AutoConfigureWireMock(port = 8080)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = { "app.eventful.baseUrl=http://localhost:8080", "app.eventful.apiKey=test",
		"app.weatherbit.baseUrl=http://localhost:8080", "app.weatherbit.apiKey=test" })
public class WeatherBitClientTest extends WiremockBase {

	@Autowired
	private WeatherBitClient client;

	@Test
	public void shouldGetCurrentWeather() {
		// Given
		createWireMockStub("/forecast/daily", "wiremock-weather-forecast.json");

		// When
		WeatherBitModel model = client.getWeatherForecast(38.12f, -78.54f);

		// Then
		assertThat(model).isNotNull();
		assertThat(model.getCityName()).isEqualTo("Free Union");
		assertThat(model.getCountryCode()).isEqualTo("US");
		assertThat(model.getLatitude()).isEqualTo(38.12f);
		assertThat(model.getLongitude()).isEqualTo(-78.54f);
		assertThat(model.getData()).isNotNull();
		assertThat(model.getData().size()).isEqualTo(16);
	}
}
