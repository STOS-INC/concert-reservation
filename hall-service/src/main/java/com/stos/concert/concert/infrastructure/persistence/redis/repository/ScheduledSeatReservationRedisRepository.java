package com.stos.concert.concert.infrastructure.persistence.redis.repository;

import static java.util.Objects.*;

import java.time.Duration;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.stos.concert.concert.domain.SeatStatus;
import com.stos.concert.concert.domain.dto.ScheduledSeatDto;
import com.stos.concert.concert.infrastructure.persistence.redis.dto.ScheduledSeatReservationEntity;
import com.stos.concert.shared.support.redis.RedisKeyType;

import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Repository
public class ScheduledSeatReservationRedisRepository implements ScheduledSeatReservationRepository {
	private static final long EXPIRE_TIME_MINUTES = 5L;
	private final RedisTemplate<String, ScheduledSeatReservationEntity> redisTemplate;

	@Override
	public boolean reserve(ScheduledSeatReservationEntity dto) {
		log.debug("[{}] >> reserve [{}]", this.getClass(), dto.getSeatId());
		final var operation = redisTemplate.opsForValue();
		final var done = operation.setIfAbsent(typeKey(dto.getSeatId()), dto, Duration.ofMinutes(EXPIRE_TIME_MINUTES));
		if (isNull(done)) {
			throw new PersistenceException("scheduled seat persistence exception");
		}
		return done;
	}

	@Override
	public void cancel(Long seatId) {
		log.debug("[{}] >> cancel reservation [{}]", this.getClass(), seatId);
		redisTemplate.delete(typeKey(seatId));
	}

	@Override
	public ScheduledSeatDto find(Long seatId) {
		final var operation = redisTemplate.opsForValue();
		final var entity = operation.get(typeKey(seatId));
		return new ScheduledSeatDto(seatId, SeatStatus.valueOf(entity.getStatus()));
	}

	@Override
	public RedisKeyType type() {
		return RedisKeyType.SEAT;
	}
}
