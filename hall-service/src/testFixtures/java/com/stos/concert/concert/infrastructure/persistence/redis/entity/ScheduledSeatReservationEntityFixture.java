package com.stos.concert.concert.infrastructure.persistence.redis.entity;

import static com.stos.concert.FixtureMonkeySupport.*;

import net.jqwik.api.Arbitraries;

import com.stos.concert.concert.infrastructure.persistence.redis.dto.ScheduledSeatReservationEntity;

public enum ScheduledSeatReservationEntityFixture {
	INSTANCE;

	public ScheduledSeatReservationEntity entity() {
		return REFLECTION_FIXTURE.giveMeBuilder(ScheduledSeatReservationEntity.class)
			.set("seatId", Arbitraries.longs())
			.set("status", Arbitraries.of("RESERVING"))
			.sample();
	}

	public ScheduledSeatReservationEntity entity(Long seatId) {
		return REFLECTION_FIXTURE.giveMeBuilder(ScheduledSeatReservationEntity.class)
			.set("seatId", seatId)
			.set("status", Arbitraries.of("RESERVING"))
			.sample();
	}
}
