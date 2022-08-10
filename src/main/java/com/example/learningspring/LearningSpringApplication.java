package com.example.learningspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// TODO: Setup Rabbit - Done
//  Producers - Done
//  Consumers - Done. Concurrent consumers max is not working as expected?
//  Routing keys on some message property? - Used the routing key property when sending message
//  With the application start, assign to queue - Done
//  Create queue from exchange for this application - Done

// TODO: WebSockets - Done using STOMP
//  Create web socket and connect UI - Done

// TODO: Security
//  OAuth with Spring and Keycloak

@SpringBootApplication
public class LearningSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearningSpringApplication.class, args);
    }
}
