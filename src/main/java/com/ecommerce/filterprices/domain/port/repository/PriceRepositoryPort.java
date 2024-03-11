package com.ecommerce.filterprices.domain.port.repository;

import com.ecommerce.filterprices.domain.model.Price;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public interface PriceRepositoryPort {


    List<Price> findAll();

    Optional<Price> findByQuery(Long productId, Long brand_id, OffsetDateTime application_date);
}
