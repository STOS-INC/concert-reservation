package com.stos.concert.shared.exception.client;

import java.io.Serial;

import com.stos.concert.shared.exception.AbstractClientException;

public class AbstractForbiddenException extends AbstractClientException {
	@Serial
	private static final long serialVersionUID = -4691286236390579762L;
	private static final int FORBIDDEN_STATUS = 403;

	protected AbstractForbiddenException() {
		super(FORBIDDEN_STATUS);
	}

	protected AbstractForbiddenException(final Throwable cause) {
		super(FORBIDDEN_STATUS, cause);
	}

	protected AbstractForbiddenException(final String reason) {
		super(FORBIDDEN_STATUS, reason);
	}

	protected AbstractForbiddenException(final String reason, final Throwable cause) {
		super(FORBIDDEN_STATUS, reason, cause);
	}
}
