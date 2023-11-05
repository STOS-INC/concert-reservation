package com.stos.concert.concert.domain.mapper;

import com.stos.concert.concert.domain.SeatStatus;
import com.stos.concert.concert.domain.dto.ScheduledSeatDto;
import com.stos.concert.concert.infrastructure.persistence.jpa.entity.ScheduledSeatEntity;

public class ScheduledSeatMapper {
	public static ScheduledSeatDto toDto(ScheduledSeatEntity source) {
		return new ScheduledSeatDto(source.getId(), SeatStatus.valueOf(source.getStatus()));
	}
}
