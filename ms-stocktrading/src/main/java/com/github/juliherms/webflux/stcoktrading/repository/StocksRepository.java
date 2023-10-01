package com.github.juliherms.webflux.stcoktrading.repository;

import com.github.juliherms.webflux.stcoktrading.model.Stock;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StocksRepository extends ReactiveMongoRepository<Stock, String> {
}
