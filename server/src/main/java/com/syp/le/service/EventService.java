package com.syp.le.service;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.syp.le.model.CustomEventModel;

/**
 * @author <a href="mailto:ricky.shiyouping@gmail.com">Ricky Shi</a>
 *
 * @since 25 Jul 2019
 */
public interface EventService {

	/**
	 * Either the 'keywords', 'location', 'category' or 'date' parameter is
	 * required.
	 */
	@Nonnull
	Page<CustomEventModel> findEvents(@Nonnull Pageable pageable, @Nullable String category, @Nullable String keywords,
			@Nullable String location, @Nullable String date);

	@Nullable
	CustomEventModel findOneById(@Nonnull String id);

	@Nonnull
	List<String> findEventfulCategories();
}
