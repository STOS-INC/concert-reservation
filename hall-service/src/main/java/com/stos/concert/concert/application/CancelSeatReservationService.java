package com.stos.concert.concert.application;

import com.stos.concert.concert.domain.service.ScheduledSeatService;
import com.stos.concert.shared.support.layered.ApplicationService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ApplicationService
public class CancelSeatReservationService implements CancelSeatReservationUsecase {
	private final ScheduledSeatService scheduledSeatService;

	@Override
	public void cancel(CancelSeatReservationCommand command) {
		scheduledSeatService.cancel(command.seatId());
	}
}
