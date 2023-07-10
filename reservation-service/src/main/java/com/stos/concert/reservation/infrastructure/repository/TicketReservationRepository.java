package com.stos.concert.reservation.infrastructure.repository;

import com.stos.concert.reservation.infrastructure.entity.TicketReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketReservationRepository extends JpaRepository<TicketReservation, Long> {

}
