package com.ecommerce.filterprices.infrastructure.adapter.db;

import com.ecommerce.filterprices.domain.model.Price;
import com.ecommerce.filterprices.domain.port.repository.PriceRepositoryPort;
import com.ecommerce.filterprices.infrastructure.adapter.db.mapper.PriceMapper;
import com.ecommerce.filterprices.infrastructure.adapter.db.repository.PriceRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class DbPriceRepository  implements PriceRepositoryPort {


    private PriceRepository priceRepository;

    private final PriceMapper mapper = Mappers.getMapper(PriceMapper.class);

    @Autowired
    public DbPriceRepository(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }


    @Override
    public List<Price> findAll() {
        return priceRepository.findAll().stream().map(mapper::convert).toList();
    }

    public Optional<Price> findByQuery(Long productId, Long brandId, OffsetDateTime applicationDate) {

        return priceRepository
                .findTopByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc( productId,brandId,applicationDate,applicationDate)
                .map(mapper::convert);
    }
}
