package com.kafka.example.kafkaslackexample.service;

import com.kafka.example.kafkaslackexample.SlackInfo;
import com.slack.api.Slack;
import com.slack.api.methods.SlackApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class SlackService {

    private final SlackInfo slackInfo;

    public void sendMessageToSlack(String message) {
        Slack slackInstance = Slack.getInstance();
        try {
            slackInstance
                    .methods(slackInfo.getToken())
                    .chatPostMessage(
                            chatPostMessageRequestBuilder -> chatPostMessageRequestBuilder
                                    .channel(slackInfo.getChannel())
                                    .text(message)
                    );
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SlackApiException e) {
            e.printStackTrace();
        }
    }
}