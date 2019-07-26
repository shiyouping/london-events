package com.syp.le.response;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.concurrent.Callable;

import javax.annotation.Nonnull;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.syp.le.dto.BaseDto;

/**
 * A response wrapper for a {@linkplain Page} of {@linkplain BaseDto}s.
 * 
 * @author <a href="mailto:ricky.shiyouping@gmail.com">Ricky Shi</a>
 * 
 * @since 25 Jul 2019
 * 
 * @param <D> subclass of {@linkplain BaseDto}
 *
 */
public class PageResponse<D extends BaseDto> implements Callable<ResponseEntity<Page<D>>> {

	private final Callable<D> callable;

	public PageResponse(@Nonnull Callable<D> callable) {
		checkNotNull(callable, "callable cannot be null");
		this.callable = callable;
	}

	@Override
	public ResponseEntity<Page<D>> call() throws Exception {
		Page<D> body = callable.call();
		return new ResponseEntity<>(body, HttpStatus.OK);
	}

	/**
	 * 
	 * Defines how to get the {@linkplain Page} of {@linkplain BaseDto}
	 * 
	 * @author <a href="mailto:ricky.shiyouping@gmail.com">Ricky Shi</a>
	 * 
	 * @since 25 Jul 2019
	 *
	 * @param <D> subclass of {@linkplain BaseDto}
	 */
	@FunctionalInterface
	public static interface Callable<D extends BaseDto> {

		@Nonnull
		Page<D> call() throws Exception;
	}
}
