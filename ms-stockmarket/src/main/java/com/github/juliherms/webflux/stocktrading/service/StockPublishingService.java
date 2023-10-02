package com.github.juliherms.webflux.stocktrading.service;

import com.github.juliherms.webflux.stocktrading.dto.StockPublishRequest;
import com.github.juliherms.webflux.stocktrading.dto.StockPublishResponse;
import com.github.juliherms.webflux.stocktrading.exception.StockPublishingException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class StockPublishingService {

    public Mono<StockPublishResponse> publishStock(StockPublishRequest request) {
        return Mono.just(request)
                .map(this::persistStock);
    }

    /**
     * Simulate to persist request
     * @param request
     * @return
     */
    private StockPublishResponse persistStock(StockPublishRequest request) {
        return StockPublishResponse.builder()
                .price(request.getPrice())
                .stockName(request.getStockName())
                .currencyName(request.getCurrencyName())
                .status(getStatus(request))
                .build();
    }

    private String getStatus(StockPublishRequest request) {
        if(request.getStockName().contains("-"))
            throw new StockPublishingException(
                    "Stock name contains illegal character '-'");
        return  "SUCCESS";
    }
}
