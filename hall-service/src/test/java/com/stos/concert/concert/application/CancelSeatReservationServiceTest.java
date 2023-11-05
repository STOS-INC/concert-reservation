package com.stos.concert.concert.application;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.stos.concert.concert.domain.dto.ScheduledSeatDtoFixture;
import com.stos.concert.concert.domain.service.ScheduledSeatService;

@ExtendWith(MockitoExtension.class)
public class CancelSeatReservationServiceTest {

	@Mock
	private ScheduledSeatService scheduledSeatService;
	@InjectMocks
	private CancelSeatReservationService reserveSeatService;

	@Test
	void reserve() {
		// given
		final var seatId = ScheduledSeatDtoFixture.INSTANCE.seatId();
		final var command = new CancelSeatReservationUsecase.CancelSeatReservationCommand(seatId);
		doNothing().when(scheduledSeatService).cancel(seatId);

		// when
		reserveSeatService.cancel(command);

		// then
		verify(scheduledSeatService).cancel(seatId);
	}
}
