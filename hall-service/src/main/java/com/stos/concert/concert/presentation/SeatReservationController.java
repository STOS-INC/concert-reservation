package com.stos.concert.concert.presentation;

import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stos.concert.concert.presentation.response.SeatReservationResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Seats", description = "Concert Seats API")
@RequestMapping("/v1/seats/reservation")
public interface SeatReservationController {
	@GetMapping("{seatId}")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "좌석 예약 여부 확인", content = @Content(schema = @Schema(implementation = SeatReservationResponse.class))),
		@ApiResponse(responseCode = "404", description = "좌석 예약 여부를 찾지 못했습니다.", content = @Content(schema = @Schema(implementation = ProblemDetail.class)))})
	@Operation(summary = "좌석 예약 확인 API", description = "좌석을 확인한다.")
	SeatReservationResponse findSeatReservation(@PathVariable Long seatId);

	@PostMapping("{seatId}")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "좌석 예약"),
		@ApiResponse(responseCode = "404", description = "좌석을 찾지 못했습니다.", content = @Content(schema = @Schema(implementation = ProblemDetail.class))),
		@ApiResponse(responseCode = "500", description = "예약 실패", content = @Content(schema = @Schema(implementation = ProblemDetail.class)))})
	@Operation(summary = "좌석 예약 API", description = "좌석을 예약한다.")
	ResponseEntity<?> reserveSeat(@PathVariable Long seatId);

	@DeleteMapping("/{seatId}")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "좌석 예약 취소"),
		@ApiResponse(responseCode = "500", description = "취소 실패", content = @Content(schema = @Schema(implementation = ProblemDetail.class)))})
	@Operation(summary = "좌석 예약 취소 API", description = "좌석을 예약을 취소한다.")
	ResponseEntity<?> cancelSeatReservation(@PathVariable Long seatId);
}