package com.stos.concert.shared.exception.client;

import java.io.Serial;

import com.stos.concert.shared.exception.AbstractClientException;

public class AbstractNotFoundException extends AbstractClientException {
	@Serial
	private static final long serialVersionUID = -3283872564488125244L;
	private static final int NOT_FOUND = 404;

	protected AbstractNotFoundException() {
		super(NOT_FOUND);
	}

	protected AbstractNotFoundException(final Throwable cause) {
		super(NOT_FOUND, cause);
	}

	protected AbstractNotFoundException(final String reason) {
		super(NOT_FOUND, reason);
	}

	protected AbstractNotFoundException(final String reason, final Throwable cause) {
		super(NOT_FOUND, reason, cause);
	}
}
