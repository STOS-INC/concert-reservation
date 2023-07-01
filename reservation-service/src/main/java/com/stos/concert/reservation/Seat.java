package com.stos.concert.reservation;

import jakarta.persistence.Embeddable;

@Embeddable
public class Seat {
  private String type;
  private String section;
  private String row;
  private String number;
}
