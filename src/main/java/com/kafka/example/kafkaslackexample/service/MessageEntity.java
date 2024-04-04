package com.kafka.example.kafkaslackexample.service;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Getter
@Entity
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String date;
    private String message;

    public MessageEntity() {

    }

    public MessageEntity(Long id, String date, String message) {
        this.id = id;
        this.date = date;
        this.message = message;
    }
}