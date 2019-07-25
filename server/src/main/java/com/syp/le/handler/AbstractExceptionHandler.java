package com.syp.le.handler;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Map;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import com.syp.le.response.ErrorResponse;
import com.syp.le.utils.UniqueId;

/**
 * @author <a href="mailto:ricky.shiyouping@gmail.com">Ricky Shi</a>
 * 
 * @since 25 Jul 2019
 */
public abstract class AbstractExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(SpecificExceptionHandler.class);

	@Nonnull
	protected ResponseEntity<ErrorResponse> createErrorResponse(@Nonnull Exception exception,
			@Nonnull Map<String, Object> messages, @Nonnull HttpStatus status, @Nonnull WebRequest request) {
		checkNotNull(exception, "exception cannot be null");
		checkNotNull(messages, "messages cannot be null");
		checkNotNull(status, "status cannot be null");
		checkNotNull(request, "request cannot be null");

		String code = UniqueId.get();
		String path = getPath(request);

		logger.warn("Failed to process http request. Http Status={}, Error Code={}, Requested Path={}", status, code,
				path, exception);

		ErrorResponse body = new ErrorResponse(status, messages, path, code);
		return new ResponseEntity<>(body, status);
	}

	private String getPath(WebRequest request) {
		if (!(request instanceof ServletWebRequest)) {
			return null;
		}

		ServletWebRequest servletWebRequest = (ServletWebRequest) request;
		return servletWebRequest.getRequest().getServletPath();
	}
}
