package com.syp.le.service;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:ricky.shiyouping@gmail.com">Ricky Shi</a>
 *
 * @since 29 Jul 2019
 */
@Component
public class CacheServiceImpl implements CacheService {

	private static final Logger logger = LoggerFactory.getLogger(CacheServiceImpl.class);
	private final EventService eventService;

	public CacheServiceImpl(@Nonnull EventService eventService) {
		checkNotNull(eventService, "eventService cannot be null");
		this.eventService = eventService;
	}

	@Override
	@Scheduled(fixedDelay = 60000)
	public void clearEventCacheWithFixedDelay() {
		eventService.clearEventCache();
		logger.info("****** Event cache was cleared ******");
	}
}
