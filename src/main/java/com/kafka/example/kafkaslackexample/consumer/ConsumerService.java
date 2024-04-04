package com.kafka.example.kafkaslackexample.consumer;

import com.kafka.example.kafkaslackexample.service.MessageService;
import com.kafka.example.kafkaslackexample.service.SlackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    private final MessageService messageService;
    private final SlackService slackService;

    @Autowired
    public ConsumerService(MessageService messageService, SlackService slackService) {
        this.messageService = messageService;
        this.slackService = slackService;
    }

    @KafkaListener(id = "from-kafka-topic", topics = "${kafka.topic.kafka}")
    public void listen1(String message) {
        System.out.println("message = " + message);
        messageService.saveMessage(message);
    }


    @KafkaListener(id = "from-slack-topic", topics = "${kafka.topic.slack}")
    public void listen2(String message) {
        slackService.sendMessageToSlack(message);
    }
}

