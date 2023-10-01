package com.github.juliherms.webflux.stcoktrading.exception;

public class StockNotFoundException extends RuntimeException {

    public StockNotFoundException(String message) {
        super(message);
    }
}

