package com.stos.concert.ticket.application;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketHitConsumeService {

  private final RedisTemplate<String, String> stringRedisTemplate;
  @KafkaListener(topics = "ticket-hit")
  public void ticketHitConsumer(String message) {
    stringRedisTemplate.opsForValue().set(message, "1");
  }
}
