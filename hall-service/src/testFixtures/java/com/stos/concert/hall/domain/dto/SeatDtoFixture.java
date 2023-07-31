package com.stos.concert.hall.domain.dto;

import static com.stos.concert.FixtureMonkeySupport.*;

import java.util.List;

public enum SeatDtoFixture {
	INSTANCE;

	public SeatDto dto() {
		return CONSTRUCTOR_FIXTURE.giveMeOne(SeatDto.class);
	}

	public List<SeatDto> dtoList(int size) {
		return CONSTRUCTOR_FIXTURE.giveMe(SeatDto.class, size);
	}

	public List<SeatDto> dtoList() {
		return dtoList(10);
	}
}
