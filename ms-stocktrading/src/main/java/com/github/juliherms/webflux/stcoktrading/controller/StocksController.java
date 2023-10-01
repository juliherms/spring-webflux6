package com.github.juliherms.webflux.stcoktrading.controller;

import com.github.juliherms.webflux.stcoktrading.dto.StockRequest;
import com.github.juliherms.webflux.stcoktrading.dto.StockResponse;
import com.github.juliherms.webflux.stcoktrading.service.StocksService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@AllArgsConstructor
@RestController
@RequestMapping("/stocks")
public class StocksController {

    private StocksService stocksService;

    @GetMapping("/{id}")
    public Mono<StockResponse> getOneStock(@PathVariable String id,
                                           @RequestParam(value = "currency", defaultValue = "USD") String currency) {
        return stocksService.getOneStock(id, currency);
    }

    @GetMapping
    public Flux<StockResponse> getAllStocks(
            @RequestParam(required = false, defaultValue = "0")  BigDecimal priceGreaterThan
    ) {
        return stocksService.getAllStocks(priceGreaterThan);
    }

    @PostMapping
    public Mono<StockResponse> createStock(@RequestBody StockRequest stock) {
        return stocksService.createStock(stock);
    }
}
