package com.stos.concert.concert.exception;

import java.io.Serial;

import com.stos.concert.shared.exception.client.AbstractConflictException;

public class ScheduledSeatReservingConflictException extends AbstractConflictException {
	@Serial
	private static final long serialVersionUID = -6458888507905519566L;

	public ScheduledSeatReservingConflictException(String reason) {
		super(reason);
	}
}
