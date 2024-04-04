package com.kafka.example.kafkaslackexample;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MessageDateDto {
    private String date;
    private String message;

    public static MessageDateDto createNowMessageDateDto(String message) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date nowDate = new Date();
        return new MessageDateDto(simpleDateFormat.format(nowDate), message);
    }

    public String transToJson() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Json 변환을 실패하였습니다. : " + e.getMessage());
        }
    }

    public MessageDateDto() {
    }

    public MessageDateDto(String date, String message) {
        this.date = date;
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}