package com.stos.concert.concert.domain;

public class ScheduledSeat {
	private final Long seatId;
	private final SeatStatus seatStatus;

	public ScheduledSeat(Long seatId, SeatStatus seatStatus) {
		this.seatId = seatId;
		this.seatStatus = seatStatus;
	}

	public boolean isReservable() {
		return this.seatStatus.equals(SeatStatus.FREE);
	}

	enum SeatStatus {
		FREE,
		TEMPORARY_OCCUPIED,
		RESERVED,
		CLOSED,
	}
}
