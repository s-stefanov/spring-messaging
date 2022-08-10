package com.example.learningspring.consumers;

import com.example.learningspring.Document;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PdfConsumerImpl implements PdfConsumer {

    @Override
    public void consume(final Document document) {
        log.info("Document {}", document);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
