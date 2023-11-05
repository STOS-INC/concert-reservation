package com.stos.concert.concert.infrastructure.persistence.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stos.concert.concert.infrastructure.persistence.jpa.entity.ScheduledSeatEntity;

public interface ScheduledSeatRepository extends JpaRepository<ScheduledSeatEntity, Long> {
}
