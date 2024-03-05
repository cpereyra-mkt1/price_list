package com.ecommerce.filterprices.infrastructure.adapter.file.system.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PriceFile {
    String brandId;
    String startDate;
    String endDate;
    String priceList;
    String productId;
    String priority;
    String price;
    String currency;
}
