package com.github.juliherms.webflux.stocktrading.exception;

import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class StockExceptionHandler {

    @ExceptionHandler(StockPublishingException.class)
    public ProblemDetail handleStockPublishingException(StockPublishingException ex) {
        return ex.asProblemDetail();
    }
}
