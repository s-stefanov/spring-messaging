package com.example.learningspring.producers;

import com.example.learningspring.Document;
import com.example.learningspring.config.RabbitMQProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class DocumentProducer {
    private final RabbitMQProperties rabbitMQProperties;
    private final RabbitTemplate rabbitTemplate;

    public void sendDocumentMessage(Document document) {
        log.info("Sending document, {}", document);
        rabbitTemplate.convertAndSend(
                rabbitMQProperties.getExchangeName(),
                rabbitMQProperties.getPdfConsumer().getRoutingKey(),
                document
        );
    }
}
