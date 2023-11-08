package com.stos.concert.event;

import com.stos.concert.shared.Event.DomainEvent;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerConfig;
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

  private final KafkaTemplate<String, Message<DomainEvent>> kafkaTemplate;

  public EventProducer() {
    this.kafkaTemplate = new KafkaTemplate<>(producerFactory());
  }

  private ProducerFactory<String, Message<DomainEvent>> producerFactory() {
    Map<String, Object> config = new HashMap<>();
    config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
    return new DefaultKafkaProducerFactory<>(config);
  }

  public void publish(String topic, DomainEvent domainEvent) {
    kafkaTemplate.send(topic, MessageBuilder
        .withPayload(domainEvent)
        .build());
  }
}
