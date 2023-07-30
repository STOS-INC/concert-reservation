package com.stos.concert.ticket;

import com.stos.concert.event.EventProducer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({EventProducer.class})
public class TicketServiceConfig {

}
