package com.appsdeveloper.productKafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Bean
    NewTopic createNewTopic(){
        int i =1;
        int  j = 2;
        int k = i++ + ++j;
        float result = (i+j)/2;
        System.out.println();
        return TopicBuilder.name("product-created-event-topic")
                .partitions(3)
                .replicas(3)
                .config("min.insync.replicas", "2")
                .build();



    }
}
