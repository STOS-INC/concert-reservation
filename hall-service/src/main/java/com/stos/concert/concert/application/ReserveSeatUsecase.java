package com.stos.concert.concert.application;

import org.springframework.lang.NonNull;

import com.stos.concert.concert.domain.dto.ScheduledSeatDto;

public interface ReserveSeatUsecase {
	ScheduledSeatDto reserve(ReserveSeatCommand command);

	record ReserveSeatCommand(@NonNull Long seatId) {
	}
}
