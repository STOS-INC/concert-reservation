package com.stos.concert.shared.exception;

import java.io.Serial;

public class AbstractClientException extends AbstractStosException {
	@Serial
	private static final long serialVersionUID = -3656854576924191009L;

	protected AbstractClientException(int status) {
		super(status);
	}

	protected AbstractClientException(int status, Throwable cause) {
		super(status, cause);
	}

	protected AbstractClientException(int status, String reason) {
		super(status, reason);
	}

	protected AbstractClientException(int status, String reason, Throwable cause) {
		super(status, reason, cause);
	}
}
