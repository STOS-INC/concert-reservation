package com.stos.concert.concert.infrastructure.persistence.redis.repository;

import com.stos.concert.concert.domain.dto.ScheduledSeatDto;
import com.stos.concert.concert.infrastructure.persistence.redis.dto.ScheduledSeatReservationEntity;
import com.stos.concert.shared.support.redis.RedisRepository;

public interface ScheduledSeatReservationRepository extends RedisRepository {
	boolean reserve(ScheduledSeatReservationEntity dto);

	void cancel(Long seatId);

	ScheduledSeatDto find(Long seatId);

}
