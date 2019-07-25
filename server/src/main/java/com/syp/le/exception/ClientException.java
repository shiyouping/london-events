package com.syp.le.exception;

import org.springframework.http.HttpStatus;

/**
 * @author <a href="mailto:ricky.shiyouping@gmail.com">Ricky Shi</a>
 *
 * @since 25 Jul 2019
 */
public class ClientException extends ApplicationException {

	private static final long serialVersionUID = 1L;
	private HttpStatus httpStatus;

	public ClientException(String message, Throwable cause) {
		super(message, cause);
	}

	public ClientException(String message, HttpStatus httpStatus) {
		super(message);
		this.httpStatus = httpStatus;
	}

	public ClientException(Throwable cause, HttpStatus httpStatus) {
		super(cause);
		this.httpStatus = httpStatus;
	}

	public ClientException(String message, Throwable cause, HttpStatus httpStatus) {
		super(message, cause);
		this.httpStatus = httpStatus;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
}
