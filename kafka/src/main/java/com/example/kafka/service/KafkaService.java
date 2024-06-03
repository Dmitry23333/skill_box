package com.example.kafka.service;

import com.example.kafka.model.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaService {
    @Value("${app.kafka.kafkaStatusTopic}")
    private String topicName;
    private final KafkaTemplate<String, Status> kafkaTemplate;

    public void send(Status status) {
        kafkaTemplate.send(topicName, status);
    }
}
