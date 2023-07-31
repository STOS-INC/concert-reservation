package com.stos.concert.concert.domain.dto;

import static com.stos.concert.FixtureMonkeySupport.*;

import net.jqwik.api.Arbitraries;

import com.stos.concert.concert.domain.SeatStatus;

public enum ScheduledSeatDtoFixture {
	INSTANCE;

	public Long seatId() {
		return Arbitraries.longs().sample();
	}

	public ScheduledSeatDto dto() {
		return CONSTRUCTOR_FIXTURE.giveMeBuilder(ScheduledSeatDto.class)
			.setNotNull("seatId")
			.setNotNull("seatStatus")
			.sample();
	}

	public ScheduledSeatDto freeDto() {
		return CONSTRUCTOR_FIXTURE.giveMeBuilder(ScheduledSeatDto.class)
			.set("seatId", Arbitraries.longs())
			.set("seatStatus", SeatStatus.FREE)
			.sample();
	}
}
