package com.stos.concert.concert.application;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.stos.concert.concert.domain.dto.ScheduledSeatDtoFixture;
import com.stos.concert.concert.domain.service.ScheduledSeatService;

@ExtendWith(MockitoExtension.class)
public class ReserveSeatServiceTest {

	@Mock
	private ScheduledSeatService scheduledSeatService;
	@InjectMocks
	private ReserveSeatService reserveSeatService;

	@Test
	void reserve() {
		// given
		final var dto = ScheduledSeatDtoFixture.INSTANCE.dto();
		final var seatId = dto.getSeatId();
		final var command = new ReserveSeatUsecase.ReserveSeatCommand(seatId);
		doReturn(dto).when(scheduledSeatService).reserve(seatId);

		// when
		final var actual = reserveSeatService.reserve(command);

		// then
		assertThat(actual).isEqualTo(dto);
	}
}
