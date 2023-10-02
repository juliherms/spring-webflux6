package com.github.juliherms.webflux.stocktrading.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockPublishResponse {
    private String stockName;
    private BigDecimal price;
    private String currencyName;
    private String status;
}
