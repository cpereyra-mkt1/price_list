package com.ecommerce.filterprices.domain.port.service;


import com.ecommerce.filterprices.domain.model.Price;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public interface PriceService {

    List<Price> getPriceDetails();

    Optional<Price> getPriceByParams(Long product_id, Long brand_id, OffsetDateTime application_date);

}
