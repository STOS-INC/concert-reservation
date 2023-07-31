package com.stos.concert.concert.domain.service;

import static com.stos.concert.concert.domain.SeatStatus.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.stos.concert.concert.exception.ScheduledSeatNotFoundException;
import com.stos.concert.concert.exception.ScheduledSeatReservedConflictException;
import com.stos.concert.concert.exception.ScheduledSeatReservingConflictException;
import com.stos.concert.concert.infrastructure.persistence.jpa.entity.ScheduledSeatEntityFixture;
import com.stos.concert.concert.infrastructure.persistence.jpa.repository.ScheduledSeatRepository;
import com.stos.concert.concert.infrastructure.persistence.redis.dto.ScheduledSeatReservationEntity;
import com.stos.concert.concert.infrastructure.persistence.redis.entity.ScheduledSeatReservationEntityFixture;
import com.stos.concert.concert.infrastructure.persistence.redis.repository.ScheduledSeatReservationRepository;
import com.stos.concert.shared.exception.AbstractStosException;

@ExtendWith(MockitoExtension.class)
public class ScheduledSeatServiceImplTest {

	@Mock
	private ScheduledSeatRepository scheduledSeatRepository;

	@Mock
	private ScheduledSeatReservationRepository scheduledSeatReservationRepository;
	@InjectMocks
	private ScheduledSeatServiceImpl service;

	@Test
	void reserve_shouldBeSuccess() {
		// given : 정상 데이터가 주어졌을 때
		final var entity = ScheduledSeatEntityFixture.INSTANCE.freeEntity();
		final var seatId = entity.getId();
		final var reservationEntity = ScheduledSeatReservationEntityFixture.INSTANCE.entity(seatId);
		doReturn(Optional.of(entity)).when(scheduledSeatRepository).findById(seatId);
		doReturn(true).when(scheduledSeatReservationRepository).reserve(any(ScheduledSeatReservationEntity.class));

		// when :
		final var actual = service.reserve(seatId);

		// then
		assertThat(actual.getSeatId()).isEqualTo(seatId);
		assertThat(actual.getSeatStatus()).isEqualTo(RESERVING);
	}

	@Test
	void reserve_shouldBeThrowScheduledSeatNotFoundException_whenSeatIdIsInvalid() {
		// given : scheduledSeat 가 존재하지 않는 데이터일 경우
		final var seatId = ScheduledSeatEntityFixture.INSTANCE.seatId();
		doReturn(Optional.empty()).when(scheduledSeatRepository).findById(seatId);

		// when : 예외가 발생하면
		final var actual = assertThrows(AbstractStosException.class, () -> {
			service.reserve(seatId);
		});

		// then : ScheduledSeatNotFoundException 이 발생한다
		assertThat(actual).isInstanceOf(ScheduledSeatNotFoundException.class);
	}

	@Test
	void reserve_shouldBeThrowScheduledSeatReservedConflictException_whenSeatIdIsAlreadyReserved() {
		// given : 이미 예약된 좌석 데이터가 주어질 경우
		final var entity = ScheduledSeatEntityFixture.INSTANCE.reservedEntity();
		final var seatId = entity.getId();
		doReturn(Optional.of(entity)).when(scheduledSeatRepository).findById(seatId);

		// when : 예외가 발생하면
		final var actual = assertThrows(AbstractStosException.class, () -> {
			service.reserve(seatId);
		});

		// then : ScheduledSeatNotFoundException 이 발생한다
		assertThat(actual).isInstanceOf(ScheduledSeatReservedConflictException.class);
	}

	@Test
	void reserve_shouldBeThrowScheduledSeatReservingConflictException_whenSeatIdIsAlreadyReserving() {
		// given : 좌석은 Free 인데 이미 좌석이 점유된 경우
		final var entity = ScheduledSeatEntityFixture.INSTANCE.freeEntity();
		final var seatId = entity.getId();
		doReturn(Optional.of(entity)).when(scheduledSeatRepository).findById(seatId);
		doReturn(false).when(scheduledSeatReservationRepository).reserve(any(ScheduledSeatReservationEntity.class));

		// when : 예외가 발생하면
		final var actual = assertThrows(AbstractStosException.class, () -> {
			service.reserve(seatId);
		});

		// then : ScheduledSeatNotFoundException 이 발생한다
		assertThat(actual).isInstanceOf(ScheduledSeatReservingConflictException.class);
	}
}
