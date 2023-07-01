package com.stos.concert.reservation;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TicketReservation {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String thumbnail;
  private String name;
  private LocalDateTime concertDateTime;
  private LocalDateTime registeredDate;
  private String hallName;
  private LocalDateTime reservationDateTime;
  private String reservationStatus;
  @ElementCollection
  private List<Seat> seats;

  public Boolean isExpiredDateTicket() {
    return LocalDateTime.now().isAfter(concertDateTime);
  }

}
