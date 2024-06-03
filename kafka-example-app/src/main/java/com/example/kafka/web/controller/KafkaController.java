package com.example.kafka.web.controller;

import com.example.kafka.event.OrderEvent;
import com.example.kafka.model.Order;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/kafka")
@RequiredArgsConstructor
public class KafkaController {
    @Value("${app.kafka.kafkaMessageTopic}")
    private String topicName;
    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;
    private final ModelMapper modelMapper;

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody Order message) {
        kafkaTemplate.send(topicName, modelMapper.map(message, OrderEvent.class));
        return ResponseEntity.ok("Message sent to kafka");
    }
}
