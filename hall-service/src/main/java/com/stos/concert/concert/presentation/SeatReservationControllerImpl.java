package com.stos.concert.concert.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.stos.concert.concert.application.CancelSeatReservationUsecase;
import com.stos.concert.concert.application.ReserveSeatUsecase;
import com.stos.concert.concert.presentation.response.SeatReservationResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SeatReservationControllerImpl implements SeatReservationController {
	private final ReserveSeatUsecase reserveSeatUsecase;
	private final CancelSeatReservationUsecase cancelSeatReservationUsecase;

	@Override
	public SeatReservationResponse findSeatReservation(Long seatId) {
		return null;
	}

	@Override
	public ResponseEntity<?> reserveSeat(Long seatId) {
		final var command = new ReserveSeatUsecase.ReserveSeatCommand(seatId);
		final var response = reserveSeatUsecase.reserve(command);
		final var uri = ServletUriComponentsBuilder.fromCurrentServletMapping()
			.path("/v1/seats/reservation/")
			.path(String.valueOf(response.getSeatId()))
			.build().toUri();
		return ResponseEntity.created(uri).build();
	}

	@Override
	public ResponseEntity<?> cancelSeatReservation(Long seatId) {
		final var command = new CancelSeatReservationUsecase.CancelSeatReservationCommand(seatId);
		cancelSeatReservationUsecase.cancel(command);
		return ResponseEntity.noContent().build();
	}
}
