package com.stos.concert.concert.domain.dto;

import static com.stos.concert.FixtureMonkeySupport.*;

public enum ConcertDtoFixture {
	INSTANCE;

	public ConcertDto dto() {
		return CONSTRUCTOR_FIXTURE.giveMeOne(ConcertDto.class);
	}
}

