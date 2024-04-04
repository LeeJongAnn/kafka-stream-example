package com.kafka.example.kafkaslackexample.streams;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kafka.example.kafkaslackexample.StreamInfo;
import com.kafka.example.kafkaslackexample.TopicNames;
import com.kafka.example.kafkaslackexample.service.MessageEntity;
import com.kafka.example.kafkaslackexample.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;

import java.util.HashMap;

@Configuration
@EnableKafkaStreams
@RequiredArgsConstructor
public class KafkaStreamConfig {

    private final MessageService messageService;
    private final TopicNames topicNames;
    private final StreamInfo streamInfo;

    @Bean
    public KStream<String, String> kafkaStream(StreamsBuilder streamsBuilder) {
        KStream<String, String> stream = streamsBuilder.stream(topicNames.getKafka());
        stream.mapValues(value -> {
                    try {
                        MessageEntity messageEntity = messageService.getMessageEntity(value);
                        return messageService.transMessageToString(messageEntity);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException("메시지를 슬랙에 보낼 수 없습니다.");
                    }
                })
                .to(topicNames.getSlack());
        return stream;
    }

    @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
    public KafkaStreamsConfiguration kafkaStreamsConfiguration() {
        HashMap<String, Object> configs = new HashMap<>();
        configs.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, streamInfo.getServers());
        configs.put(StreamsConfig.APPLICATION_ID_CONFIG, streamInfo.getName());
        configs.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        configs.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        return new KafkaStreamsConfiguration(configs);
    }
}
