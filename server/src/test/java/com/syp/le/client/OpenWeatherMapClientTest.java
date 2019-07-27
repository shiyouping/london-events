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

import com.syp.le.model.CurrentWeatherModel;

@RunWith(SpringRunner.class)
@AutoConfigureWireMock(port = 8080)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = { "app.eventful.baseUrl=http://localhost:8080", "app.eventful.apiKey=test",
		"app.openWeatherMap.baseUrl=http://localhost:8080", "app.openWeatherMap.apiKey=test" })
public class OpenWeatherMapClientTest extends WiremockBase {

	@Autowired
	private OpenWeatherMapClient client;

	@Test
	public void shouldGetCurrentWeather() {
		// Given
		createWireMockStub("/weather", "wiremock-current-weather.json");

		// When
		CurrentWeatherModel currentWeatherModel = client.getCurrentWeather(51.54f, -0.02f);

		// Then
		assertThat(currentWeatherModel).isNotNull();
		assertThat(currentWeatherModel.getName()).isEqualTo("Hackney");
		assertThat(currentWeatherModel.getTime()).isEqualTo(1564150542l);
		assertThat(currentWeatherModel.getTimezone()).isEqualTo(3600);
		assertThat(currentWeatherModel.getWeather()).isNotNull();
		assertThat(currentWeatherModel.getWeather().size()).isEqualTo(1);
		assertThat(currentWeatherModel.getWeather().get(0).getMain()).isEqualTo("Clouds");
		assertThat(currentWeatherModel.getWeather().get(0).getDescription()).isEqualTo("scattered clouds");
		assertThat(currentWeatherModel.getWeather().get(0).getIcon()).isEqualTo("03d");
		assertThat(currentWeatherModel.getWeather().get(0).getId()).isEqualTo("802");
	}
}
