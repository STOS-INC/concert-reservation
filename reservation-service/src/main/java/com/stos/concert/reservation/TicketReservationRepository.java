package com.stos.concert.reservation;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketReservationRepository extends JpaRepository<TicketReservation, Long> {

}
