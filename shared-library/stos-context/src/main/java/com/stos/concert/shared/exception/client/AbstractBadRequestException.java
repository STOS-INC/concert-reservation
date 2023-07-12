package com.stos.concert.shared.exception.client;

import java.io.Serial;

import com.stos.concert.shared.exception.AbstractClientException;

public class AbstractBadRequestException extends AbstractClientException {
	@Serial
	private static final long serialVersionUID = 7431545695355960902L;
	private static final int BAD_REQUEST_STATUS = 400;

	protected AbstractBadRequestException() {
		super(BAD_REQUEST_STATUS);
	}

	protected AbstractBadRequestException(Throwable cause) {
		super(BAD_REQUEST_STATUS, cause);
	}

	protected AbstractBadRequestException(String reason) {
		super(BAD_REQUEST_STATUS, reason);
	}

	protected AbstractBadRequestException(String reason, Throwable cause) {
		super(BAD_REQUEST_STATUS, reason, cause);
	}
}
