package com.syp.le.response;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.concurrent.Callable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.syp.le.dto.BaseDto;

/**
 * 
 * A response wrapper for a {@linkplain BaseDto}. Return 404 if the list is
 * empty.
 * 
 * @author <a href="mailto:ricky.shiyouping@gmail.com">Ricky Shi</a>
 * 
 * @since 25 Jul 2019
 *
 * @param <D> subclass of {@linkplain BaseDto}
 */
public class SingleResponse<D extends BaseDto> implements Callable<ResponseEntity<D>> {

	private final Callable<D> callable;

	public SingleResponse(@Nonnull Callable<D> callable) {
		checkNotNull(callable, "callable cannot be null");
		this.callable = callable;
	}

	@Override
	public ResponseEntity<D> call() throws Exception {
		D body = callable.call();

		if (body == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(body, HttpStatus.OK);
	}

	/**
	 * Defines how to get the {@linkplain BaseDto}
	 * 
	 * @author <a href="mailto:ricky.shiyouping@gmail.com">Ricky Shi</a>
	 * @since Jun 28, 2019
	 *
	 * @param <D> subclass of {@linkplain BaseDto}
	 */
	@FunctionalInterface
	public static interface Callable<D extends BaseDto> {

		@Nullable
		D call() throws Exception;
	}
}
