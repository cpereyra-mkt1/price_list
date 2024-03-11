package com.ecommerce.filterprices.infrastructure.adapter.db.repository;

import com.ecommerce.filterprices.infrastructure.adapter.db.entity.PriceEntity;
import jakarta.annotation.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.OffsetDateTime;
import java.util.Optional;

public interface PriceRepository extends JpaRepository<PriceEntity, Long> {
    Optional<PriceEntity>findTopByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(@Nullable Long productId, @Nullable Long brandId, @Nullable OffsetDateTime startDate, OffsetDateTime endDate); // 1.0.0
}
