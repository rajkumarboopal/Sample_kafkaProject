package com.appsdeveloper.productKafka.controller;


import com.appsdeveloper.productKafka.error.ErrorMessage;
import com.appsdeveloper.productKafka.model.CreateProductModel;
import com.appsdeveloper.productKafka.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RestController("/products")
public class ProductController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    ProductService productService;

//    public ProductController(ProductService productService){
//        this.productService = productService;
//    }
    @PostMapping("/create")
    public ResponseEntity<Object> createProducts(@RequestBody CreateProductModel createProductModel){
        try {
            String productId = productService.createProduct(createProductModel);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.debug(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorMessage(new Date(), e.getMessage(), "/product"));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("");
    }
}
