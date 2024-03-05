package com.ecommerce.filterprices.domain.port.file.system;

import com.ecommerce.filterprices.domain.model.Price;

import java.io.IOException;
import java.util.Set;

public interface IPriceFileSystem {
    Set<Price> getAll() throws IOException;
}
