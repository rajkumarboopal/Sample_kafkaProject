package com.appsdeveloper.productKafka.controller;


import com.appsdeveloper.productKafka.model.CreateProductModel;
import com.appsdeveloper.productKafka.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/products")
public class ProductController {


    ProductService productService;

//    public ProductController(ProductService productService){
//        this.productService = productService;
//    }
    @PostMapping("/create")
    public ResponseEntity<String> createProducts(@RequestBody CreateProductModel createProductModel){
        String productId = productService.createProduct(createProductModel);
        return ResponseEntity.status(HttpStatus.CREATED).body("");
    }
}
