package com.ecommerce.filterprices.infrastructure.adapter.api.controller;

import com.ecommerce.filterprices.api.infrastructure.api.definition.ApiUtil;
import com.ecommerce.filterprices.api.infrastructure.api.definition.PriceApi;
import com.ecommerce.filterprices.api.infrastructure.api.domain.Price;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public class PriceApiController implements PriceApi {

    @Override
    public ResponseEntity<List<Price>> getPrices(
            @Parameter(name = "application_date", description = "application date") @Valid @RequestParam(value = "application_date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Optional<OffsetDateTime> applicationDate,
            @Parameter(name = "chain_id", description = "chain id") @Valid @RequestParam(value = "chain_id", required = false) Optional<String> chainId,
            @Parameter(name = "product_id", description = "product id") @Valid @RequestParam(value = "product_id", required = false) Optional<String> productId
    ) throws Exception {

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


}