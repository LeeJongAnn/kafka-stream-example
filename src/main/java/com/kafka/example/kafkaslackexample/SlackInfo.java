package com.kafka.example.kafkaslackexample;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "slack")
@Getter
@Setter
public class SlackInfo {
    private String token;
    private String channel;
}