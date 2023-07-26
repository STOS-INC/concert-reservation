package com.stos.concert.ticket;

import com.stos.concert.event.EventProducer;
import com.stos.concert.redis.RedisConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({RedisConfig.class, EventProducer.class})
public class TicketServiceConfig {

}
