package com.github.juliherms.webflux.stcoktrading.client;

import com.github.juliherms.webflux.stcoktrading.dto.client.CurrencyRateResponse;
import com.github.juliherms.webflux.stcoktrading.dto.client.StockPublishRequest;
import com.github.juliherms.webflux.stcoktrading.dto.client.StockPublishResponse;
import com.github.juliherms.webflux.stcoktrading.exception.StockCreationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@Slf4j
public class StockMarketClient {

    private WebClient webClient;

    public StockMarketClient(@Value("${clients.stockMarket.baseUrl}") String baseUrl){
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .filter(ExchangeFilterFunction.ofRequestProcessor( //add header trace for log
                        request -> Mono.just(ClientRequest.from(request)
                                .header("X-Trace-Id", UUID.randomUUID().toString())
                                .build())
                ))
                .build();
    }

    public Flux<CurrencyRateResponse> getCurrencyRates() {
        return webClient.get()
                .uri("/currencyRates")
                .retrieve()
                .bodyToFlux(CurrencyRateResponse.class);
    }

    public Mono<StockPublishResponse> publishStock(
            StockPublishRequest requestBody) {
        return webClient.post()
                .uri("/stocks/publish")
                .body(BodyInserters.fromValue(requestBody))
                .exchangeToMono(response ->
                        !response.statusCode().isError() ? //verify error
                                response.bodyToMono(StockPublishResponse.class) : //success
                                response.bodyToMono(ProblemDetail.class) //error
                                        .flatMap(problemDetail -> Mono.error(new StockCreationException(problemDetail.getDetail()))))
                .doFirst(() -> log.info("Calling Publish Stock API with Request Body: {}", requestBody))
                .doOnNext(spr -> log.info("Publish Stock API Response: {}", spr));
    }
}
