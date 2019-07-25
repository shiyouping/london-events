package com.syp.le.mapper;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.collections4.CollectionUtils;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.google.common.collect.Lists;
import com.syp.le.dto.BaseDto;
import com.syp.le.model.BaseModel;

/**
 * Mapper between {@linkplain BaseDto} and {@linkplain BaseModel}
 * 
 * @author <a href="mailto:ricky.shiyouping@gmail.com">Ricky Shi</a>
 * 
 * @since 25 Jul 2019
 *
 * @param <Dto>   subtype of {@linkplain BaseDto}
 * @param <Model> subtype of {@linkplain BaseModel}
 */
public interface BaseMapper<Dto extends BaseDto, Model extends BaseModel> {

	/**
	 * Convert from {@linkplain BaseDto} to {@linkplain BaseModel}
	 */
	@Nullable
	Model toModel(@Nullable Dto dto);

	/**
	 * Convert from a list of {@linkplain BaseDto}s to a list of
	 * {@linkplain BaseModel}s
	 */
	@Nullable
	default List<Model> toModelList(@Nullable List<Dto> dtos) {

		if (dtos == null || dtos.size() == 0) {
			return null;
		}

		List<Model> models = new ArrayList<>();

		for (Dto dto : dtos) {
			models.add(toModel(dto));
		}

		return models;
	}

	/**
	 * Convert from a page of {@linkplain BaseDto}s to a page of
	 * {@linkplain BaseModel}s
	 *
	 */
	@Nonnull
	default Page<Model> toModelPage(@Nonnull Page<Dto> page, @Nonnull Pageable pageable) {
		checkNotNull(page, "page cannot be null");
		checkNotNull(pageable, "pageable cannot be null");

		List<Model> content = Lists.newArrayListWithCapacity(0);

		if (CollectionUtils.isNotEmpty(page.getContent())) {
			content = toModelList(page.getContent());
		}

		return new PageImpl<>(content, pageable, page.getTotalElements());
	}

	/**
	 * Convert from {@linkplain BaseModel} to {@linkplain BaseDto}
	 */
	@Nullable
	Dto toDto(@Nullable Model model);

	/**
	 * Convert from a list of {@linkplain BaseModel}s to a list of
	 * {@linkplain BaseDto}s
	 */
	@Nullable
	default List<Dto> toDtoList(@Nullable List<Model> models) {

		if (CollectionUtils.isEmpty(models)) {
			return null;
		}

		List<Dto> dtos = Lists.newArrayListWithExpectedSize(models.size());

		for (Model model : models) {
			dtos.add(toDto(model));
		}

		return dtos;
	}

	/**
	 * Convert from a page of {@linkplain BaseModel}s to a page of
	 * {@linkplain BaseDto}s
	 */
	@Nonnull
	default Page<Dto> toDtoPage(@Nonnull Page<Model> page, @Nonnull Pageable pageable) {
		checkNotNull(page, "page cannot be null");
		checkNotNull(pageable, "pageable cannot be null");

		List<Dto> content = Lists.newArrayListWithCapacity(0);

		if (CollectionUtils.isNotEmpty(page.getContent())) {
			content = toDtoList(page.getContent());
		}

		return new PageImpl<>(content, pageable, page.getTotalElements());
	}

	/**
	 * Update the passed {@linkplain BaseModel}
	 */
	Model updateModel(@Nullable Dto source, @Nullable @MappingTarget Model toBeUpdatedTarget);

	/**
	 * Update the passed {@linkplain BaseDto}
	 */
	Dto updateDto(@Nullable Model source, @Nullable @MappingTarget Dto toBeUpdatedTarget);
}
