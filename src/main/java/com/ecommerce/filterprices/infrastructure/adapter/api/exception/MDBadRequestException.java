package com.ecommerce.filterprices.infrastructure.adapter.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class MDBadRequestException extends RuntimeException{

    public MDBadRequestException() {
    }

    public MDBadRequestException(String message) {
        super(message);
    }

    public MDBadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
