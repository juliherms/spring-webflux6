package com.github.juliherms.webflux.stcoktrading.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class Stock {
    @Id
    private String id;
    private String name;
    @NonNull
    private BigDecimal price;
    private String currency;
}
