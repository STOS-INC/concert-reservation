package com.stos.concert.shared.exception.client;

import java.io.Serial;

import com.stos.concert.shared.exception.AbstractClientException;

public class AbstractUnauthorizedException extends AbstractClientException {
	@Serial
	private static final long serialVersionUID = 2410205489641574413L;
	private static final int UNAUTHORIZED_STATUS = 401;

	protected AbstractUnauthorizedException() {
		super(UNAUTHORIZED_STATUS);
	}

	protected AbstractUnauthorizedException(final Throwable cause) {
		super(UNAUTHORIZED_STATUS, cause);
	}

	protected AbstractUnauthorizedException(final String reason) {
		super(UNAUTHORIZED_STATUS, reason);
	}

	protected AbstractUnauthorizedException(final String reason, final Throwable cause) {
		super(UNAUTHORIZED_STATUS, reason, cause);
	}
}
