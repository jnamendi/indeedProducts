package com.indeed.poc.products.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@AllArgsConstructor
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MissingParameterException extends RuntimeException {
    private final String message;
    private final String details;
}
