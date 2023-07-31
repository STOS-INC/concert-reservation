package com.stos.concert.concert.exception;

import java.io.Serial;

import com.stos.concert.shared.exception.client.AbstractConflictException;

public class ScheduledSeatReservedConflictException extends AbstractConflictException {
	@Serial
	private static final long serialVersionUID = -165815392468589375L;
	
	public ScheduledSeatReservedConflictException(String reason) {
		super(reason);
	}
}
