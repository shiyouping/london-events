package com.syp.le.utils;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nonnull;

import org.springframework.context.ApplicationContextException;

import com.alibaba.fastjson.JSON;
import com.syp.le.model.EventfulErrorModel;

/**
 * @author <a href="mailto:ricky.shiyouping@gmail.com">Ricky Shi</a>
 *
 * @since Jul 27, 2019
 */
public class EventfulUtil {

	private static final String errorValue = "1";

	private EventfulUtil() {
	}

	/**
	 * Check if the response from eventful is an error
	 *
	 */
	public static void checkErrorResponse(@Nonnull String response) {
		checkNotNull(response, "response cannot be null");

		EventfulErrorModel model = JSON.parseObject(response, EventfulErrorModel.class);

		if (errorValue.equals(model.getError())) {
			throw new ApplicationContextException(response);
		}
	}
}
