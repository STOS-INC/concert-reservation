package com.stos.concert.shared.exception.server;

import java.io.Serial;

import com.stos.concert.shared.exception.AbstractServerException;

public class AbstractInternalServerErrorException extends AbstractServerException {
	@Serial
	private static final long serialVersionUID = -6461665590760155443L;
	private static final int INTERNAL_SERVER_ERROR_STATUS = 500;

	protected AbstractInternalServerErrorException() {
		super(INTERNAL_SERVER_ERROR_STATUS);
	}

	protected AbstractInternalServerErrorException(final Throwable cause) {
		super(INTERNAL_SERVER_ERROR_STATUS, cause);
	}

	protected AbstractInternalServerErrorException(final String reason) {
		super(INTERNAL_SERVER_ERROR_STATUS, reason);
	}

	protected AbstractInternalServerErrorException(final String reason, final Throwable cause) {
		super(INTERNAL_SERVER_ERROR_STATUS, reason, cause);
	}
}
