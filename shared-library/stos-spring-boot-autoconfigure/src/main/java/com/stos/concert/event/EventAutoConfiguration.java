package com.stos.concert.event;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class EventAutoConfiguration {

  @Bean
  public EventProducer eventProducer() {
    return new EventProducer();
  }
}
