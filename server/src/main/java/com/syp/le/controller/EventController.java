package com.syp.le.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.syp.le.client.EventfulClient;
import com.syp.le.model.EventfulModel;
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
	private EventfulClient client;

	@GetMapping
	public VoidResponse getEvents() {

		return new VoidResponse(() -> {
			EventfulModel model = client.searchEvents(null, "London", null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null);
			System.out.println(model);
		});
	}
}
