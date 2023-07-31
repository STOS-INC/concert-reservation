package com.stos.concert.concert.domain.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import com.stos.concert.hall.domain.dto.HallDto;

//TODO : 티켓이 아니라 콘서트가 더 맞는것같다... 티켓 = 언제(일정) 어디서(콘서트홀) 어떻게(좌석) 무엇을(콘서트) 이 정해진 결과물이지 않나...?
//TODO : 공연의 타입을 관리해야하지 않을까? 타입별로 취할 수 있는 행위가 달라질 수 있다. (e.g : 뮤지컬이라고 하면 상영일정에 따라 배우가 달라진다던가...? 공연별로 공지사항등을 관리할수도 있을 것 같다)
public class ConcertDto {
	private final Long id;
	private final String title;
	private final String description;
	private final String mainImageUrl;
	private final List<String> detailImageUrls;
	private final HallDto hall;
	private final List<ScheduleDto> schedules;
	private final LocalDateTime reservableStartDateTime;
	private final LocalDateTime reservableEndDateTime;
	private final long hit;

	public ConcertDto(Long id, String title, String description, String mainImageUrl, List<String> detailImageUrls,
		HallDto hall, List<ScheduleDto> scheduleDtos, LocalDateTime reservableStartDateTime,
		LocalDateTime reservableEndDateTime,
		long hit) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.mainImageUrl = mainImageUrl;
		this.detailImageUrls = detailImageUrls;
		this.hall = hall;
		this.schedules = scheduleDtos;
		this.reservableStartDateTime = reservableStartDateTime;
		this.reservableEndDateTime = reservableEndDateTime;
		this.hit = hit;
	}

	public boolean isSoldOut() {
		return schedules.stream()
			.allMatch(ScheduleDto::isSoldOut);
	}

	private LocalDate getConcertStartDate() {
		return schedules.stream()
			.min(Comparator.comparing(ScheduleDto::getStartDateTime))
			.map(ScheduleDto::getStartDateTime)
			.get() //TODO : 예외를 터트릴지 기본값을 넣을지 정해야함.
			.toLocalDate();
	}

	private LocalDate getConcertLastDate() {
		return schedules.stream()
			.max(Comparator.comparing(ScheduleDto::getStartDateTime))
			.map(ScheduleDto::getStartDateTime)
			.get()
			.toLocalDate();
	}

	public void increaseHit() {

	}

}
