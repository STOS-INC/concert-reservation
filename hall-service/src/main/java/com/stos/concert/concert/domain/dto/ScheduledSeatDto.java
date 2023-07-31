package com.stos.concert.concert.domain.dto;

import com.stos.concert.concert.domain.SeatStatus;

import lombok.Getter;

@Getter
public class ScheduledSeatDto {
	private Long seatId;
	private SeatStatus seatStatus;

	public ScheduledSeatDto(Long seatId, SeatStatus seatStatus) {
		this.seatId = seatId;
		this.seatStatus = seatStatus;
	}

	public void reserving() {
		this.seatStatus = SeatStatus.RESERVING;
	}

	public boolean isReservable() {
		return this.seatStatus.equals(SeatStatus.FREE);
	}

	public boolean cannotReserve() {
		return !isReservable();
	}

}
