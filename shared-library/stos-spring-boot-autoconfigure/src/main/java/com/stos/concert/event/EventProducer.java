package com.stos.concert.event;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class EventProducer {

  private final KafkaTemplate<String, String> kafkaTemplate;

  public String publish(String topic, String message) {
    kafkaTemplate.send(topic, message);
    return message;
  }
}
