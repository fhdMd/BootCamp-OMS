package com.example.demo.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic userCreatedTopic(){
        return TopicBuilder.name("user.created").build();
    }

    @Bean
    public NewTopic legacyCreateUserTopic(){
        return TopicBuilder.name("user.created.legacy").build();
    }
}
