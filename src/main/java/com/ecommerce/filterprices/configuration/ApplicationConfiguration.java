package com.ecommerce.filterprices.configuration;

import com.ecommerce.filterprices.domain.port.file.system.IPriceFileSystem;
import com.ecommerce.filterprices.infrastructure.adapter.file.system.PriceFileSystem;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {



    @Bean
    public IPriceFileSystem priceFileSystem() {
        return new PriceFileSystem();
    }
}

