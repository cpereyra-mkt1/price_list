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
    private Long brandId;
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;
    private Long priceList;
    private Long productId;
    private Long priority;
    private Double price;
    private String currency;
}

