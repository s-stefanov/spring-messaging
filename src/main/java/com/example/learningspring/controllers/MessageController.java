package com.example.learningspring.controllers;

import com.example.learningspring.ConvertDocumentMessage;
import com.example.learningspring.Document;
import com.example.learningspring.producers.DocumentProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MessageController {
    private final DocumentProducer documentProducer;
    private final SimpMessagingTemplate template;

    @PostMapping("/documents")
    public ResponseEntity<?> createDocument() {
        final Document document = new Document();
        document.setTitle("Some title");
        document.setBody("With cool body");

        documentProducer.sendDocumentMessage(document);

        template.convertAndSend("/topic/convert-document", document);

        return ResponseEntity.ok().build();
    }
}
