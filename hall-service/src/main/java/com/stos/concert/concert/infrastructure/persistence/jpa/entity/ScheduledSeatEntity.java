package com.stos.concert.concert.infrastructure.persistence.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "scheduled_seat")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ScheduledSeatEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String status;
	private Long scheduleId;

	public void changeStatus(String status) {
		this.status = status;
	}
}