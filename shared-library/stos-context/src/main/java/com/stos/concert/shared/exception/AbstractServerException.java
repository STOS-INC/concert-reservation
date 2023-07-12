package com.stos.concert.shared.exception;

import java.io.Serial;

public class AbstractServerException extends AbstractStosException {
	@Serial
	private static final long serialVersionUID = 6072712245497836954L;

	protected AbstractServerException(int status) {
		super(status);
	}

	protected AbstractServerException(int status, Throwable cause) {
		super(status, cause);
	}

	protected AbstractServerException(int status, String reason) {
		super(status, reason);
	}

	protected AbstractServerException(int status, String reason, Throwable cause) {
		super(status, reason, cause);
	}
}
