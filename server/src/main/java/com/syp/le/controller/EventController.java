package com.syp.le.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.syp.le.client.OpenWeatherMapClient;
import com.syp.le.model.CurrentWeatherModel;
import com.syp.le.response.VoidResponse;

/**
 * @author <a href="mailto:ricky.shiyouping@gmail.com">Ricky Shi</a>
 *
 * @since 25 Jul 2019
 */
@RestController
@RequestMapping("/api/v1/events")
public class EventController {

	@Autowired
	private OpenWeatherMapClient client;

	@GetMapping
	public VoidResponse getEvents() {
		return new VoidResponse(() -> {
			CurrentWeatherModel model = client.getCurrentWeather(51.544393f, -0.0227277f);
			System.out.println(model);
		});
	}
}
