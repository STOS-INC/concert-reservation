package com.stos.concert.hall.domain.dto;

import static com.stos.concert.FixtureMonkeySupport.*;

public enum HallDtoFixture {

	INSTANCE;

	public HallDto dto() {
		return CONSTRUCTOR_FIXTURE.giveMeBuilder(HallDto.class)
			.set("seats", SeatDtoFixture.INSTANCE.dtoList())
			.sample();
	}
}
