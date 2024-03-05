package com.ecommerce.filterprices.infrastructure.adapter.file.system;

import com.ecommerce.filterprices.domain.model.Price;
import com.ecommerce.filterprices.domain.port.file.system.IPriceFileSystem;
import com.ecommerce.filterprices.infrastructure.adapter.file.system.mapper.PriceFileMapper;
import com.ecommerce.filterprices.infrastructure.adapter.file.system.model.PriceFile;
import org.apache.commons.csv.CSVRecord;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.ecommerce.filterprices.infrastructure.adapter.file.system.util.FileSystemUtils.cleanRecord;
import static com.ecommerce.filterprices.infrastructure.adapter.file.system.util.FileSystemUtils.getCsvRecords;

public class PriceFileSystem implements IPriceFileSystem {
    private final PriceFileMapper mapper = Mappers.getMapper(PriceFileMapper.class);

    @Value("${file.csv.price}")
    String fileName;

    @Override
    public Set<Price> getAll() throws IOException {
        Set<PriceFile> prices = new HashSet<>();
        Iterable<CSVRecord> csvRecords = getCsvRecords(fileName);
        csvRecords.forEach(record ->
                prices.add(
                        PriceFile.builder()
                                .brandId(cleanRecord(record.get(0)))
                                .startDate(cleanRecord(record.get(1)))
                                .endDate(cleanRecord(record.get(2)))
                                .price(cleanRecord(record.get(3)))
                                .productId(cleanRecord(record.get(4)))
                                .priority(cleanRecord(record.get(5)))
                                .price(cleanRecord(record.get(6)))
                                .currency(cleanRecord(record.get(7)))
                                .build()
                )
        );
        return prices.stream().map(mapper::convert)
                .collect(Collectors.toSet());

    }

    public void setFileName(String s) {
        this.fileName = s;
    }

    public String getFileName() {
        return fileName;
    }
}
