package com.stos.concert.concert.domain.service;

import static org.apache.commons.lang3.BooleanUtils.*;

import org.springframework.stereotype.Service;

import com.stos.concert.concert.domain.dto.ScheduledSeatDto;
import com.stos.concert.concert.domain.mapper.ScheduledSeatMapper;
import com.stos.concert.concert.exception.ScheduledSeatConfirmedConflictException;
import com.stos.concert.concert.exception.ScheduledSeatNotFoundException;
import com.stos.concert.concert.exception.ScheduledSeatReservingConflictException;
import com.stos.concert.concert.infrastructure.persistence.jpa.repository.ScheduledSeatRepository;
import com.stos.concert.concert.infrastructure.persistence.redis.dto.ScheduledSeatReservationEntity;
import com.stos.concert.concert.infrastructure.persistence.redis.repository.ScheduledSeatReservationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScheduledSeatServiceImpl implements ScheduledSeatService {

	private final ScheduledSeatRepository scheduledSeatRepository;
	private final ScheduledSeatReservationRepository reservationRepository;

	@Override
	public ScheduledSeatDto findScheduledSeat(Long seatId) {
		final var entity = scheduledSeatRepository.findById(seatId)
			.orElseThrow(ScheduledSeatNotFoundException::new);
		return ScheduledSeatMapper.toDto(entity);
	}

	/**
	 * @exception ScheduledSeatNotFoundException invalid seat id or data not found
	 * @exception ScheduledSeatConfirmedConflictException seat already confirmed (이미 예약 확정된 좌석)
	 * @exception ScheduledSeatReservingConflictException seat already reserving (이미 예약중인 좌석)
	 */
	@Override
	public ScheduledSeatDto reserve(Long seatId) {
		final var seat = scheduledSeatRepository.findById(seatId)
			.map(entity -> ScheduledSeatMapper.toDto(entity))
			.orElseThrow(ScheduledSeatNotFoundException::new);
		if (seat.cannotReserve()) {
			throw new ScheduledSeatConfirmedConflictException(
				"scheduled seat is already confirmed : [" + seat.getSeatId() + "]");
		}
		seat.reserving();
		final var done = reservationRepository.reserve(
			new ScheduledSeatReservationEntity(seat.getSeatId(), seat.getSeatStatus().toString()));
		if (isFalse(done)) {
			throw new ScheduledSeatReservingConflictException(
				"scheduled seat is already reserving : [" + seat.getSeatId() + "]");
		}
		return seat;
	}

	@Override
	public void confirm(Long seatId) {
		// TODO: 예약 확정을 위한 메소드 (콘서트 예매가 완료됐을 때 호출)
	}

	@Override
	public void cancel(Long seatId) {
		reservationRepository.cancel(seatId);
	}
}
