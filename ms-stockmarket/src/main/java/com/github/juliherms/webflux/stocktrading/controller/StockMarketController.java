package com.github.juliherms.webflux.stocktrading.controller;

import com.github.juliherms.webflux.stocktrading.dto.StockPublishRequest;
import com.github.juliherms.webflux.stocktrading.dto.StockPublishResponse;
import com.github.juliherms.webflux.stocktrading.model.CurrencyRate;
import com.github.juliherms.webflux.stocktrading.service.CurrencyRatesService;
import com.github.juliherms.webflux.stocktrading.service.StockPublishingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@Slf4j
public class StockMarketController {

    private CurrencyRatesService currencyRatesService;
    private StockPublishingService stockPublishingService;

    @GetMapping("/currencyRates")
    public Flux<CurrencyRate> getCurrencyRates(
            @RequestHeader(value = "X-Trace-Id", required = false)
            String traceId) {
        log.info("Get Currency Rates API called with Trace Id: {}", traceId);
        return currencyRatesService.getCurrencyRates();
    }

    @PostMapping("/stocks/publish")
    public Mono<StockPublishResponse> publishStock(@RequestBody StockPublishRequest request,
                                                   @RequestHeader(value = "X-Trace-Id", required = false) String traceId) {
        log.info("Publish Stock API called with Trace Id: {}", traceId);
        return stockPublishingService.publishStock(request);
    }
}
