package com.example.learningspring.consumers;

import com.example.learningspring.Document;
import org.springframework.stereotype.Component;

public interface PdfConsumer {
    void consume(Document document);
}
