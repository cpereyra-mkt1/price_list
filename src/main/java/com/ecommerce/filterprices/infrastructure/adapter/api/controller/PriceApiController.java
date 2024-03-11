package com.ecommerce.filterprices.infrastructure.adapter.api.controller;


import com.ecommerce.filterprices.domain.port.service.PriceService;
import com.ecommerce.filterprices.infrastructure.adapter.api.PriceApi;
import com.ecommerce.filterprices.infrastructure.adapter.api.domain.PriceApiModel;
import com.ecommerce.filterprices.infrastructure.adapter.api.exception.PMNotFoundException;
import com.ecommerce.filterprices.infrastructure.adapter.api.mapper.PriceApiMapper;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.NativeWebRequest;

import java.time.OffsetDateTime;
import java.util.Optional;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-03-09T09:59:23.088864-03:00[America/Argentina/Buenos_Aires]")
@Controller
public class PriceApiController implements PriceApi {


    private final NativeWebRequest request;

    private final PriceService service;

    private final PriceApiMapper mapper = Mappers.getMapper(PriceApiMapper.class);

    @Autowired
    public PriceApiController(NativeWebRequest request, PriceService service) {
        this.request = request;
        this.service = service;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }
    @Override
    public ResponseEntity<PriceApiModel> getPrice(
            @Parameter(name = "application_date", description = "application date") @Valid @RequestParam(value = "application_date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Optional<OffsetDateTime> applicationDate,
            @Parameter(name = "chain_id", description = "chain id") @Valid @RequestParam(value = "chain_id", required = false) Optional<Long> chainId,
            @Parameter(name = "product_id", description = "product id") @Valid @RequestParam(value = "product_id", required = false) Optional<Long> productId
    ) throws Exception {

        return new ResponseEntity<>(service.getPriceByParams(productId.orElse(null),chainId.orElse(null),applicationDate.orElse(null))
                .map(mapper::convert)
                .orElseThrow(() -> new PMNotFoundException("no records found for this query")),HttpStatus.OK);
    }
}
