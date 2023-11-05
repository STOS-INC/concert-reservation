package com.stos.concert.concert.infrastructure.persistence.redis.dto;

import com.stos.concert.shared.support.redis.RedisEntity;

import lombok.Getter;

@Getter
public class ScheduledSeatReservationEntity implements RedisEntity {
	private Long seatId;
	private String status;

	public ScheduledSeatReservationEntity() {
	}

	public ScheduledSeatReservationEntity(Long seatId, String status) {
		this.seatId = seatId;
		this.status = status;
	}

	@Override
	public String id() {
		return String.valueOf(seatId);
	}

}
