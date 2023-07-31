package com.stos.concert.hall.domain.dto;

import java.util.List;

public class HallDto {
	private final Long id;
	private final String name;
	private final List<SeatDto> seats;

	public HallDto(Long id, String name, List<SeatDto> seats) {
		this.id = id;
		this.name = name;
		this.seats = seats;
	}
}
