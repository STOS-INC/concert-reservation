package com.stos.concert.concert.infrastructure.persistence.jpa.entity;

import static com.stos.concert.FixtureMonkeySupport.*;

import net.jqwik.api.Arbitraries;

public enum ScheduledSeatEntityFixture {
	INSTANCE;

	private Long id;
	private String status;
	private Long scheduleId;

	public Long seatId() {
		return Arbitraries.longs().sample();
	}

	public ScheduledSeatEntity entity() {
		return REFLECTION_FIXTURE.giveMeOne(ScheduledSeatEntity.class);
	}

	public ScheduledSeatEntity freeEntity() {
		return entity("FREE");
	}

	public ScheduledSeatEntity reservedEntity() {
		return entity("RESERVED");
	}

	public ScheduledSeatEntity entity(String status) {
		return REFLECTION_FIXTURE.giveMeBuilder(ScheduledSeatEntity.class)
			.set("id", Arbitraries.longs())
			.set("status", status)
			.set("scheduleId", Arbitraries.longs())
			.sample();
	}
}
