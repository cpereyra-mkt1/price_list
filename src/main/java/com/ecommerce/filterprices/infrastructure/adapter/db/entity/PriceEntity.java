package com.ecommerce.filterprices.infrastructure.adapter.db.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name="price")
public class PriceEntity {

    @Id
    private Long id;
    private Long brandId;
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;
    private Long priceList;
    private Long productId;
    private Long priority;
    private Double price;
    private String currency;
}
