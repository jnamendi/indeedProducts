package com.indeed.poc.products.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.Objects;

@Slf4j
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleException(Exception ex, WebRequest request) throws Exception {
        log(ex);
        if (ex instanceof HttpMessageNotReadableException) {
            return new ResponseEntity<>("Json input could not be parsed", HttpStatus.BAD_REQUEST);
        } else if (ex instanceof MissingPathVariableException) {
            return new ResponseEntity<>("Missing path variable", HttpStatus.BAD_REQUEST);
        } else if (ex instanceof NoResourceFoundException) {
            return new ResponseEntity<>("Missing path variable on resource", HttpStatus.BAD_REQUEST);
        }
        else {
            return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    private void log(Exception e) {
        log.warn(e.getMessage(), e);

    }
}
