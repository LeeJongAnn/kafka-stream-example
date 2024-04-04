package com.kafka.example.kafkaslackexample;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "kafka.stream")
@Getter
@Setter
public class StreamInfo {
    private String name;
    private String servers;
}