package com.syp.le.utils;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.syp.le.exception.ApplicationException;
import com.syp.le.model.EventfulErrorModel;

/**
 * @author <a href="mailto:ricky.shiyouping@gmail.com">Ricky Shi</a>
 *
 * @since Jul 27, 2019
 */
public class EventfulUtil {

	private static final String ERROR_VALUE = "1";
	private static final String EVENT_SORT_DIRECTION_ASCENDING = "ascending";
	private static final String EVENT_SORT_DIRECTION_DESCENDING = "descending";
	private static final List<String> EVENT_SORT_ORDERS = Lists.newArrayList("popularity", "date", "relevance");

	private EventfulUtil() {
	}

	/**
	 * Check if the response from eventful is an error
	 *
	 */
	public static void checkErrorResponse(@Nonnull String response) {
		checkNotNull(response, "response cannot be null");

		EventfulErrorModel model = JSON.parseObject(response, EventfulErrorModel.class);

		if (ERROR_VALUE.equals(model.getError())) {
			throw new ApplicationException(response);
		}
	}

	/**
	 * Get eventful event sort order from Spring {@link Pageable}
	 */
	public static String getEventSortOrder(@Nullable Pageable pageable) {
		if (pageable == null || pageable.getSort() == null || pageable.getSort().iterator() == null) {
			return null;
		}

		while (pageable.getSort().iterator().hasNext()) {
			String property = pageable.getSort().iterator().next().getProperty();
			if (EVENT_SORT_ORDERS.contains(property)) {
				return property;
			}
		}

		return null;
	}

	/**
	 * Get eventful event sort direction from Spring {@link Pageable}
	 */
	public static String getEventSortDirection(@Nullable Pageable pageable) {
		if (pageable == null || pageable.getSort() == null || pageable.getSort().iterator() == null) {
			return null;
		}

		while (pageable.getSort().iterator().hasNext()) {
			Direction direction = pageable.getSort().iterator().next().getDirection();
			if (direction.equals(Direction.ASC)) {
				return EVENT_SORT_DIRECTION_ASCENDING;
			} else if (direction.equals(Direction.DESC)) {
				return EVENT_SORT_DIRECTION_DESCENDING;
			}
		}

		return null;
	}
}
