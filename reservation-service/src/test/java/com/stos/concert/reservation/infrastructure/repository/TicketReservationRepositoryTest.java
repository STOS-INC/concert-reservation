package com.stos.concert.reservation.infrastructure.repository;

import static org.assertj.core.api.Assertions.*;

import com.stos.concert.reservation.infrastructure.entity.Seat;
import com.stos.concert.reservation.infrastructure.entity.TicketReservation;
import java.time.LocalDateTime;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TicketReservationRepositoryTest {
  @Autowired
  private TicketReservationRepository ticketReservationRepository;

  @Test
  void test() {
    TicketReservation ticketReservation = TicketReservation.builder()
        .thumbnail("thumbnail")
        .name("name")
        .concertDateTime(LocalDateTime.now().plusWeeks(1))
        .registeredDate(LocalDateTime.now().minusWeeks(1))
        .hallName("hallName")
        .reservationDateTime(LocalDateTime.now())
        .reservationStatus("booked")
        .seats(Arrays.asList(Seat.builder()
                .type("VIP")
                .section("section_v")
                .rowNumber("A")
                .number("1").build(),
            Seat.builder()
                .type("VIP")
                .section("section_v")
                .rowNumber("A")
                .number("2").build()))
        .build();

    TicketReservation ticketReservation1 = ticketReservationRepository.save(ticketReservation);

    TicketReservation findRes = ticketReservationRepository.findById(ticketReservation1.getId()).get();
    assertThat(findRes.getSeats().get(0).getSection()).isEqualTo("section_v");
  }
}
