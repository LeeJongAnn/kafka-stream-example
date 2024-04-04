package com.kafka.example.kafkaslackexample;

import com.kafka.example.kafkaslackexample.producer.ProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final ProducerService producerService;

    @PostMapping("/api/message")
    public void receiveMessage(@RequestParam("message") String message){
        producerService.sendMessageToKafka(message);
    }
}
