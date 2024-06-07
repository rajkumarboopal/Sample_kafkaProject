package com.appsdeveloper.productKafka.service;

import com.appsdeveloper.productKafka.model.CreateProductModel;
import org.springframework.stereotype.Service;

@Service
public interface ProductService{

    String createProduct(CreateProductModel createProductModel) throws Exception;

}
