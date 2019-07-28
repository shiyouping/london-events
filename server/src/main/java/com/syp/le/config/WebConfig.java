package com.syp.le.config;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.concurrent.ThreadPoolExecutor;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Configurations for Spring MVC
 * 
 * @author <a href="mailto:ricky.shiyouping@gmail.com">Ricky Shi</a>
 * 
 * @since 25 Jul 2019
 */
@Configuration
@ServletComponentScan
public class WebConfig {

	private static final Logger logger = LoggerFactory.getLogger(WebConfig.class);
	private final AppConfig appConfig;

	@Autowired
	public WebConfig(@Nonnull AppConfig appConfig) {
		checkNotNull(appConfig, "appConfig cannot be null");
		this.appConfig = appConfig;
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public WebMvcConfigurer webMvcConfigurerAdapter() {
		logger.info("Creating instance of WebMvcConfigurer");
		return new WebMvcAdapter();
	}

	@Bean
	public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
		logger.info("Creating instance of ThreadPoolTaskExecutor");
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		taskExecutor.setCorePoolSize(appConfig.getTaskExecutor().getCorePoolSize());
		taskExecutor.setMaxPoolSize(appConfig.getTaskExecutor().getMaxPoolSize());
		taskExecutor.setQueueCapacity(appConfig.getTaskExecutor().getQueueCapacity());
		taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
		taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
		return taskExecutor;
	}

	private class WebMvcAdapter extends WebMvcConfigurerAdapter {

		@Override
		public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
			logger.info("Configurating async support");
			configurer.setTaskExecutor(threadPoolTaskExecutor());
			super.configureAsyncSupport(configurer);
		}
	}
}
