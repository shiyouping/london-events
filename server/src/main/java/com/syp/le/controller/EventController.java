package com.syp.le.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.syp.le.client.EventfulClient;
import com.syp.le.client.OpenWeatherMapClient;
import com.syp.le.model.EventfulModel.EventModel;
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

	@Autowired
	private EventfulClient eventfulClient;

	@GetMapping
	public VoidResponse getEvents() {
		return new VoidResponse(() -> {
			EventModel model = eventfulClient.getAnEvent("E0-001-126369173-9");
			System.out.println(model);
		});
	}
}
