package com.ecommerce.filterprices.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Price {
    Long brandId;
    OffsetDateTime startDate;
    OffsetDateTime endDate;
    Long priceList;
    Long productId;
    Long priority;
    Long price;
    String currency;
}

