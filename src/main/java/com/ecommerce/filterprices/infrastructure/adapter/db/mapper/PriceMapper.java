package com.ecommerce.filterprices.infrastructure.adapter.db.mapper;

import com.ecommerce.filterprices.domain.model.Price;
import com.ecommerce.filterprices.infrastructure.adapter.db.entity.PriceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PriceMapper {

    @Mapping(target = "id", ignore = true)
    PriceEntity convert(Price model);

    Price convert(PriceEntity entity);
}
