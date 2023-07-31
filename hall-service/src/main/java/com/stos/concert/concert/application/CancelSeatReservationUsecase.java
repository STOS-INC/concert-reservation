package com.stos.concert.concert.application;

import org.springframework.lang.NonNull;

public interface CancelSeatReservationUsecase {
	void cancel(CancelSeatReservationCommand command);

	record CancelSeatReservationCommand(@NonNull Long seatId) {
	}
}
