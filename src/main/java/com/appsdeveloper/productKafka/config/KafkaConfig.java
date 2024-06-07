package com.appsdeveloper.productKafka.config;

import com.appsdeveloper.productKafka.service.ProductCreatedEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Configuration
public class KafkaConfig {

    @Value("${spring.kafka.producer.bootstrap-server}")
    private String bootstrapServers;

    @Value("${spring.kafka.producer.key-serializer}")
    private String keySerializer;

    @Value("${spring.kafka.producer.value-serializer}")
    private String valueSerializer;

    @Value("${spring.kafka.producer.acks}")
    private String asks;

    @Value("${spring.kafka.producer.retries}")
    private String reteries;

    @Value("${spring.kafka.producer.properties.delivery.timeout.ms}")
    private String timeout;

    @Value("${spring.kafka.producer.properties.linger.ms}")
    private String lingerms;

    @Value("${spring.kafka.producer.properties.request.timeout.ms}")
    private String requestTimeout;

    @Value("${spring.kafka.producer.properties.enable.idempotence}")
    private String idempotence;

    @Value("${spring.kafka.producer.properties.max.in.flight.requests.per.connection}")
    private String inFlightRequest;

    Map<String, Object> producerConfig(){
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializer);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerializer);
        config.put(ProducerConfig.ACKS_CONFIG, asks);
        config.put(ProducerConfig.DELIVERY_TIMEOUT_MS_CONFIG, timeout);

        config.put(ProducerConfig.LINGER_MS_CONFIG, lingerms);
        config.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG,requestTimeout );

        config.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, idempotence);
        config.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, inFlightRequest)
        config.put(ProducerConfig.ACKS_CONFIG, Integer.MAX_VALUE);

        return config;
    }

    @Bean
    ProducerFactory<String, ProductCreatedEvent> producerFactory(){
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }

    @Bean
    KafkaTemplate<String,ProductCreatedEvent> kafkaTemplate(){
        return new KafkaTemplate<>(producerFactory())
    }
    @Bean
    NewTopic createNewTopic(){

        return TopicBuilder.name("product-created-event-topic")
                .partitions(3)
                .replicas(3)
                .config("min.insync.replicas", "2")
                .build();



    }
}
