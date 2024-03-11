package com.ecommerce.filterprices.application;

import com.ecommerce.filterprices.domain.model.Price;
import com.ecommerce.filterprices.domain.port.repository.PriceRepositoryPort;
import com.ecommerce.filterprices.domain.port.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PriceFinder implements PriceService {
    private final PriceRepositoryPort priceRepository;
    @Autowired
    public PriceFinder(PriceRepositoryPort priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public List<Price> getPriceDetails() {
        return null;
        //return priceRepository.findAll().stream().map(PriceMapper::convert).toList(););
    }

    @Override
    public Optional<Price> getPriceByParams(Long product_id, Long brand_id, OffsetDateTime application_date) {
        return priceRepository.findByQuery(product_id, brand_id, application_date);
    }
}


