package com.appsdeveloper.productKafka.service;

import com.appsdeveloper.productKafka.model.CreateProductModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;


public class ProductServiceImpl implements ProductService{
    KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate;
    private final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    public ProductServiceImpl(KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;

    }
    @Override
    public String createProduct(CreateProductModel createProductModel) throws Exception{
        String productId = UUID.randomUUID().toString();
        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent(productId,
                createProductModel.getTitle(), createProductModel.getPrice(), createProductModel.getQuantity());
        LOGGER.info("Before publishing a product create information");
        //Asynchronus message process
        /*CompletableFuture<SendResult<String,ProductCreatedEvent>> sendResultCompletableFuture = kafkaTemplate
                .send("product-created-event-topic", productId, productCreatedEvent);
        sendResultCompletableFuture.whenComplete((result, exception)->{
            if(exception != null){
                logger.error("Failed to sent message "+exception.getMessage());
            }else{
                logger.info("Message sent successfully: "+ result.getRecordMetadata());
            }
        });*/
        //Syncronous message process
        SendResult<String,ProductCreatedEvent> sendResult = kafkaTemplate
                .send("product-created-event-topic", productId, productCreatedEvent).get();
        LOGGER.info("Partitions:"+ sendResult.getRecordMetadata().partition());
        LOGGER.info("Topic:"+ sendResult.getRecordMetadata().topic());
        LOGGER.info("OffSet:"+ sendResult.getRecordMetadata().offset());
        /*kafka> ./bin/kafka-console-consumer.bat -topic topicname
        --bootstrap-server localhost:9002 --property print.key=true
        * */
        return productId;
    }
}
