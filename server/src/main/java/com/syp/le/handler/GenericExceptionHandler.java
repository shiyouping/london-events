package com.syp.le.handler;

import java.util.Map;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.google.common.collect.Maps;
import com.syp.le.response.ErrorResponse;

/**
 * Handle exceptions that cannot be handled by other handlers
 * 
 * @author <a href="mailto:ricky.shiyouping@gmail.com">Ricky Shi</a>
 * 
 * @since 25 Jul 2019
 *
 */
@ControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class GenericExceptionHandler extends AbstractExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleException(Exception exception, WebRequest request) {
		Map<String, Object> messages = Maps.newHashMapWithExpectedSize(1);
		messages.put(ErrorResponse.MESSAGE_KEY_DETAIL, "Internal Server Error");
		return createErrorResponse(exception, messages, HttpStatus.INTERNAL_SERVER_ERROR, request);
	}
}
