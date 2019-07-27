package com.syp.le.client;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.syp.le.model.EventCategoryModel;
import com.syp.le.model.EventfulModel;
import com.syp.le.model.EventfulModel.EventModel;

/**
 * @author <a href="mailto:ricky.shiyouping@gmail.com">Ricky Shi</a>
 *
 * @since 25 Jul 2019
 */
public interface EventfulClient {

	/**
	 * Search events on https://www.eventful.com <br>
	 * 
	 * See detailed document at https://api.eventful.com/docs/events/search
	 */
	@Nullable
	EventfulModel searchEvents(@Nullable String keywords, @Nullable String location, @Nullable String date,
			@Nullable String category, @Nullable String excludedCategory, @Nullable Integer within,
			@Nullable String units, @Nullable Boolean countryOnly, @Nullable String sortOrder,
			@Nullable String sortDirection, @Nullable Integer pageSize, @Nullable Integer pageNumber,
			@Nullable String imageSizes, @Nullable Integer languages, @Nullable String mature, @Nullable String include,
			@Nullable Boolean changeMultiDayStart);

	/**
	 * Get an event by ID
	 * 
	 * See detailed document at https://api.eventful.com/docs/events/get
	 */
	@Nullable
	EventModel getAnEvent(@Nonnull String id);

	/**
	 * Get event categories
	 * 
	 * See detailed document at https://api.eventful.com/docs/categories/list
	 */
	@Nullable
	EventCategoryModel getCategoryList();
}
