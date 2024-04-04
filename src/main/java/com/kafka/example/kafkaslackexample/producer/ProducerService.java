package com.kafka.example.kafkaslackexample.producer;

import com.kafka.example.kafkaslackexample.MessageDateDto;
import com.kafka.example.kafkaslackexample.TopicNames;
import com.kafka.example.kafkaslackexample.service.MessageEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProducerService {

    private final TopicNames topicNames;
    private final KafkaTemplate kafkaTemplate;

    public void sendMessageToKafka(String message) {
        MessageDateDto nowMessageDateDto = MessageDateDto.createNowMessageDateDto(message);
        String data = nowMessageDateDto.transToJson();

        kafkaTemplate.send(topicNames.getKafka(), data).addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
                    @Override
                    public void onFailure(Throwable ex) {
                        log.error(ex.getMessage(), ex);
                    }

                    @Override
                    public void onSuccess(SendResult<String, String> result) {
                        log.info(result.toString());
                    }
                });
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