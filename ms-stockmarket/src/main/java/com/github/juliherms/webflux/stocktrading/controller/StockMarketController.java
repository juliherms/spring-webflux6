package com.github.juliherms.webflux.stocktrading.controller;

import com.github.juliherms.webflux.stocktrading.model.CurrencyRate;
import com.github.juliherms.webflux.stocktrading.service.CurrencyRatesService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@AllArgsConstructor
@Slf4j
public class StockMarketController {

    private CurrencyRatesService currencyRatesService;

    @GetMapping("/currencyRates")
    public Flux<CurrencyRate> getCurrencyRates(
            @RequestHeader(value = "X-Trace-Id", required = false)
            String traceId) {
        log.info("Get Currency Rates API called with Trace Id: {}", traceId);
        return currencyRatesService.getCurrencyRates();
    }
}
