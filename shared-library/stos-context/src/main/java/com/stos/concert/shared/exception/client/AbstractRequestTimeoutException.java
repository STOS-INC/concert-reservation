package com.stos.concert.shared.exception.client;

import java.io.Serial;

import com.stos.concert.shared.exception.AbstractClientException;

public class AbstractRequestTimeoutException extends AbstractClientException {
	@Serial
	private static final long serialVersionUID = -5000057960293877762L;
	private static final int REQUEST_TIMEOUT_STATUS = 408;

	protected AbstractRequestTimeoutException() {
		super(REQUEST_TIMEOUT_STATUS);
	}

	protected AbstractRequestTimeoutException(final Throwable cause) {
		super(REQUEST_TIMEOUT_STATUS, cause);
	}

	protected AbstractRequestTimeoutException(final String reason) {
		super(REQUEST_TIMEOUT_STATUS, reason);
	}

	protected AbstractRequestTimeoutException(final String reason, final Throwable cause) {
		super(REQUEST_TIMEOUT_STATUS, reason, cause);
	}
}
