package com.example.learningspring.config;

import com.example.learningspring.consumers.PdfConsumer;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;

@RequiredArgsConstructor
@Configuration
public class AMQPConfig {
    private final RabbitMQProperties rabbitMQProperties;

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(rabbitMQProperties.getExchangeName());
    }

    @Bean("pdfQueue")
    Queue pdfQueue() {
        return new Queue(rabbitMQProperties.getPdfConsumer().getQueueName());
    }

    @Bean("pdfBinding")
    Binding pdfBinding(@Qualifier("pdfQueue") Queue queue, DirectExchange directExchange) {
        return BindingBuilder.bind(queue).to(directExchange).with(rabbitMQProperties.getPdfConsumer().getRoutingKey());
    }

    @Bean("pdfContainer")
    SimpleMessageListenerContainer pdfContainer(
            ConnectionFactory connectionFactory,
            @Qualifier("pdfQueue") Queue queue,
            @Qualifier("pdfListenerAdapter") MessageListenerAdapter adapter
    ) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setQueues(queue);
        container.setMessageListener(adapter);
        container.setConcurrentConsumers(20);

        return container;
    }

    @Bean("pdfListenerAdapter")
    MessageListenerAdapter pdfListenerAdapter(PdfConsumer pdfConsumer, Jackson2JsonMessageConverter messageConverter) {
        final MessageListenerAdapter listener = new MessageListenerAdapter(pdfConsumer, "consume");
        listener.setMessageConverter(messageConverter);
        return listener;
    }

    @Bean("docQueue")
    Queue docQueue() {
        return new Queue(rabbitMQProperties.getDocConsumer().getQueueName());
    }

    @Bean("docBinding")
    Binding docBinding(@Qualifier("docQueue") Queue queue, DirectExchange directExchange) {
        return BindingBuilder.bind(queue).to(directExchange).with(rabbitMQProperties.getDocConsumer().getRoutingKey());
    }

    @Bean
    public RabbitTemplate amqpTemplate(ConnectionFactory connectionFactory, Jackson2JsonMessageConverter messageConverter) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public MappingJackson2MessageConverter consumerJackson2MessageConverter() {
        return new MappingJackson2MessageConverter();
    }
}
