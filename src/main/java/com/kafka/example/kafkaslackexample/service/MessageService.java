package com.kafka.example.kafkaslackexample.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    public void saveMessage(String messageDto) {
        try {
            messageRepository.save(getMessageEntity(messageDto));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public MessageEntity getMessageEntity(String messageDto) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        MessageEntity messageEntity = mapper.readValue(messageDto, MessageEntity.class);
        return messageEntity;
    }

    public String  transMessageToString(MessageEntity messageEntity) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("메시지 : ")
                .append(messageEntity.getMessage())
                .append("/ 시간 : ")
                .append(messageEntity.getDate());
        return buffer.toString();
    }
}