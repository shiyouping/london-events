package com.syp.le.mapper;

import org.mapstruct.Mapper;

import com.syp.le.dto.CustomEventDto;
import com.syp.le.model.CustomEventModel;

/**
 * @author <a href="mailto:ricky.shiyouping@gmail.com">Ricky Shi</a>
 *
 * @since Jul 27, 2019
 */
@Mapper
public interface CustomEventMapper extends BaseMapper<CustomEventDto, CustomEventModel> {
}
