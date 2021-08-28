package com.hunzaconsulting.catererservice.message;

import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;

public interface MessageProducer {
    ListenableFuture<SendResult<String, String>> sendMessage(String topic, String key, String message);
}
