package com.example.learningspring.controllers;

import com.example.learningspring.ConvertDocumentMessage;
import com.example.learningspring.Document;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class WebSocketController {

    @MessageMapping("/convert-document")
    public Document convertDocument(@Payload final ConvertDocumentMessage message) {
        final Document document = new Document();
        document.setTitle(message.getName());
        document.setBody("some cool converted body");
        return document;
    }
}
