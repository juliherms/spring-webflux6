package com.github.juliherms.webflux.stcoktrading.dto.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyRateResponse {

    private String currencyName;
    private BigDecimal rate;

}
