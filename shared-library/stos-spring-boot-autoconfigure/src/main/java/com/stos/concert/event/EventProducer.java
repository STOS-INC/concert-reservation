package com.stos.concert.event;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.kafka.core.KafkaTemplate;

@AutoConfiguration
@RequiredArgsConstructor
public class EventProducer {

  private final KafkaTemplate<String, String> kafkaTemplate;

  public String publish(String topic, String message) {
    kafkaTemplate.send(topic, message);
    return message;
  }
}
