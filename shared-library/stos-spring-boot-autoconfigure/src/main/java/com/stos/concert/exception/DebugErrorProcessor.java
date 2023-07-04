package com.stos.concert.exception;

import org.springframework.core.Ordered;
import org.springframework.http.ProblemDetail;

public class DebugErrorProcessor implements ErrorProcessor, Ordered {
	private static final String DEBUG_PROPERTY_NAME = "debug";

	@Override
	public void process(ProblemDetail problemDetail, Throwable throwable) {
		problemDetail.setProperty(DEBUG_PROPERTY_NAME, throwable.getLocalizedMessage());
	}

	@Override
	public int getOrder() {
		return LOWEST_PRECEDENCE;
	}
}
