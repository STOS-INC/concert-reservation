package com.stos.concert.shared.exception.client;

import java.io.Serial;

import com.stos.concert.shared.exception.AbstractClientException;

public class AbstractConflictException extends AbstractClientException {
	@Serial
	private static final long serialVersionUID = 1151593051383188008L;
	private static final int CONFLICT_STATUS = 409;

	protected AbstractConflictException() {
		super(CONFLICT_STATUS);
	}

	protected AbstractConflictException(final Throwable cause) {
		super(CONFLICT_STATUS, cause);
	}

	protected AbstractConflictException(final String reason) {
		super(CONFLICT_STATUS, reason);
	}

	protected AbstractConflictException(final String reason, final Throwable cause) {
		super(CONFLICT_STATUS, reason, cause);
	}
}
