package com.example.learningspring.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "rabbitmq")
public class RabbitMQProperties {
    private String exchangeName;
    private Consumer pdfConsumer;
    private Consumer docConsumer;

    @Data
    public static class Consumer {
        private String routingKey;
        private String queueName;
    }
}
