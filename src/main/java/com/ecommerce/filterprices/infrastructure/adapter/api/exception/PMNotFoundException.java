package com.ecommerce.filterprices.infrastructure.adapter.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class PMNotFoundException extends RuntimeException{

    public PMNotFoundException() {
    }

    public PMNotFoundException(String message) {
        super(message);
    }

    public PMNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
