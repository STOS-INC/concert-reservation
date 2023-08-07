package com.stos.concert.event;

import com.stos.concert.shared.Event.Payload;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

@AutoConfiguration
@RequiredArgsConstructor
public class EventProducer {

  private final KafkaTemplate<String, Message<Payload>> kafkaTemplate;

  public EventProducer() {
    this.kafkaTemplate = new KafkaTemplate<>(producerFactory());
  }

  private ProducerFactory<String, Message<Payload>> producerFactory() {
    Map<String, Object> config = new HashMap<>();
    config.put("bootstrap.servers", "localhost:9092");
    config.put("key.serializer", StringSerializer.class);
    config.put("value.serializer", JsonSerializer.class);
    return new DefaultKafkaProducerFactory<>(config);
  }

  public void publish(String topic, Payload payload) {
    kafkaTemplate.send(topic, MessageBuilder
        .withPayload(payload)
        .build());
  }
}
