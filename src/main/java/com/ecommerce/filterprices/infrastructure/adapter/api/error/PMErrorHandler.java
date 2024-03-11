package com.ecommerce.filterprices.infrastructure.adapter.api.error;

import com.ecommerce.filterprices.infrastructure.adapter.api.exception.PMNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@Slf4j
@ControllerAdvice
public class PMErrorHandler {

    @ExceptionHandler(value = PMNotFoundException.class)
    public ResponseEntity notFoundExceptionHandler(HttpServletRequest req, PMNotFoundException e) {

        log.error("RestExceptionHandler PMErrorHandler handler. No records found for this query", e);

        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity defaultErrorHandler(HttpServletRequest req, Exception e) {

        log.error("RestExceptionHandler Exception handler. Error has been detected", e);

        return new ResponseEntity<>("Server Error. ", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
