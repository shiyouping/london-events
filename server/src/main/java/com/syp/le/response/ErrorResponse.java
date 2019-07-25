package com.syp.le.response;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;
import org.springframework.http.HttpStatus;

/**
 * The only and universal error response returned to client when something wrong
 * happens
 * 
 * @author <a href="mailto:ricky.shiyouping@gmail.com">Ricky Shi</a>
 * 
 * @since 25 Jul 2019
 *
 */
@AutoProperty
public class ErrorResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");

	public static final String MESSAGE_KEY_DETAIL = "detail";
	public static final String MESSAGE_KEY_EXCEPTION_TYPE = "exceptionType";
	public static final String MESSAGE_KEY_SUPPORTED_MEDIA_TYPES = "supportedMediaTypes";

	private final String timestamp;
	private final int status;
	private final String error;
	private final String path;
	private final Map<String, ?> messages;
	private final String code;

	public ErrorResponse(@Nonnull HttpStatus status, @Nonnull Map<String, ?> messages, @Nullable String path,
			@Nullable String code) {
		checkNotNull(status, "status cannot be null");
		checkNotNull(messages, "messages cannot be null");

		this.timestamp = OffsetDateTime.now(ZoneOffset.UTC).format(FORMATTER);
		this.status = status.value();
		this.error = status.getReasonPhrase();
		this.messages = messages;
		this.path = path;
		this.code = code;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public String getCode() {
		return code;
	}

	public Map<String, ?> getMessages() {
		return messages;
	}

	public String getPath() {
		return path;
	}

	public int getStatus() {
		return status;
	}

	public String getError() {
		return error;
	}

	@Override
	public boolean equals(Object o) {
		return Pojomatic.equals(this, o);
	}

	@Override
	public int hashCode() {
		return Pojomatic.hashCode(this);
	}

	@Override
	public String toString() {
		return Pojomatic.toString(this);
	}
}