package com.stos.concert.concert.presentation;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.stos.concert.concert.application.CancelSeatReservationUsecase;
import com.stos.concert.concert.application.ReserveSeatUsecase;
import com.stos.concert.concert.domain.dto.ScheduledSeatDtoFixture;
import com.stos.concert.concert.exception.ScheduledSeatNotFoundException;
import com.stos.concert.concert.exception.ScheduledSeatReservedConflictException;
import com.stos.concert.concert.exception.ScheduledSeatReservingConflictException;
import com.stos.concert.exception.ExceptionHandlingAutoConfiguration;

@ImportAutoConfiguration(ExceptionHandlingAutoConfiguration.class)
@WebMvcTest(SeatReservationControllerImpl.class)
public class SeatReservationControllerTest {

	@MockBean
	private ReserveSeatUsecase reserveSeatUsecase;
	@MockBean
	private CancelSeatReservationUsecase cancelSeatReservationUsecase;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void reserveSeat_shouldBe200Status_whenReserveIsSuccess() throws Exception {
		// given
		final var response = ScheduledSeatDtoFixture.INSTANCE.dto();
		final var seatId = response.getSeatId();
		final var command = new ReserveSeatUsecase.ReserveSeatCommand(seatId);
		doReturn(response).when(reserveSeatUsecase).reserve(command);

		// when
		mockMvc.perform(post("/v1/seats/reservation/{seatId}", seatId))
			.andExpect(status().isCreated());
	}

	@Test
	void reserveSeat_shouldBe409Status_whenSeatIsAlreadyReserving() throws Exception {
		// given
		final var response = ScheduledSeatDtoFixture.INSTANCE.dto();
		final var seatId = response.getSeatId();
		final var command = new ReserveSeatUsecase.ReserveSeatCommand(seatId);
		doThrow(new ScheduledSeatReservingConflictException("already reserving")).when(reserveSeatUsecase)
			.reserve(command);

		// when
		mockMvc.perform(post("/v1/seats/reservation/{seatId}", seatId))
			.andExpect(status().isConflict());
	}

	@Test
	void reserveSeat_shouldBe409Status_whenSeatIsAlreadyReserved() throws Exception {
		// given
		final var response = ScheduledSeatDtoFixture.INSTANCE.dto();
		final var seatId = response.getSeatId();
		final var command = new ReserveSeatUsecase.ReserveSeatCommand(seatId);
		doThrow(new ScheduledSeatReservedConflictException("already reserved")).when(reserveSeatUsecase)
			.reserve(command);

		// when
		mockMvc.perform(post("/v1/seats/reservation/{seatId}", seatId))
			.andExpect(status().isConflict());
	}

	@Test
	void reserveSeat_shouldBe404Status_whenSeatIsNotFound() throws Exception {
		// given
		final var response = ScheduledSeatDtoFixture.INSTANCE.dto();
		final var seatId = response.getSeatId();
		final var command = new ReserveSeatUsecase.ReserveSeatCommand(seatId);
		doThrow(new ScheduledSeatNotFoundException()).when(reserveSeatUsecase).reserve(command);

		// when
		mockMvc.perform(post("/v1/seats/reservation/{seatId}", seatId))
			.andExpect(status().isNotFound());
	}

	@Test
	void cancel_shouldBe200Status_whenCancelIsSuccess() throws Exception {
		// given
		final var seatId = ScheduledSeatDtoFixture.INSTANCE.seatId();
		final var command = new CancelSeatReservationUsecase.CancelSeatReservationCommand(seatId);
		doNothing().when(cancelSeatReservationUsecase).cancel(command);

		// when
		mockMvc.perform(delete("/v1/seats/reservation/{seatId}", seatId))
			.andExpect(status().isNoContent());
	}

}
