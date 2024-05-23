package com.appsdeveloper.productKafka.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Conditional;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@ConditionalOnBean
public class ProductCreatedEvent {

    private String ProductId;
    private String title;
    private BigDecimal price;
    private Integer quantity;
}
