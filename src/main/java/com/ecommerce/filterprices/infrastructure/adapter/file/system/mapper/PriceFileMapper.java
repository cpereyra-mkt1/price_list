package com.ecommerce.filterprices.infrastructure.adapter.file.system.mapper;

import com.ecommerce.filterprices.domain.model.Price;
import com.ecommerce.filterprices.infrastructure.adapter.file.system.model.PriceFile;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Mapper
public interface PriceFileMapper {

    Price convert(PriceFile entity);


    default OffsetDateTime map(String date) {
        LocalDateTime localDateTime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss"));
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of("UTC"));
        return zonedDateTime.toOffsetDateTime();
    }

}
