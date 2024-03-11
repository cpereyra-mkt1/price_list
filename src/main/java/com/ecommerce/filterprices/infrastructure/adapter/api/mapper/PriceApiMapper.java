package com.ecommerce.filterprices.infrastructure.adapter.api.mapper;

import com.ecommerce.filterprices.domain.model.Price;
import com.ecommerce.filterprices.infrastructure.adapter.api.domain.PriceApiModel;
import org.mapstruct.Mapper;

@Mapper
public interface PriceApiMapper {

    PriceApiModel convert(Price price);

}
