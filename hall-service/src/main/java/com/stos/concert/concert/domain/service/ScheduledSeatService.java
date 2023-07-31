package com.stos.concert.concert.domain.service;

import com.stos.concert.concert.domain.dto.ScheduledSeatDto;

/**
 * @see <a href="http://bit.ly/46WWPQs">Scheduled Seat Lifecycle</a>
 */
public interface ScheduledSeatService {
	ScheduledSeatDto findScheduledSeat(Long seatId);

	ScheduledSeatDto reserve(Long seatId);

	void confirm(Long seatId);

	void cancel(Long seatId);
}
