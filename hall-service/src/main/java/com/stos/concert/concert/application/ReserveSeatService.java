package com.stos.concert.concert.application;

import org.springframework.transaction.annotation.Transactional;

import com.stos.concert.concert.domain.dto.ScheduledSeatDto;
import com.stos.concert.concert.domain.service.ScheduledSeatService;
import com.stos.concert.shared.support.layered.ApplicationService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ApplicationService
public class ReserveSeatService implements ReserveSeatUsecase {
	private final ScheduledSeatService scheduledSeatService;

	@Override
	@Transactional
	public ScheduledSeatDto reserve(ReserveSeatCommand command) {
		return scheduledSeatService.reserve(command.seatId());
	}
}
