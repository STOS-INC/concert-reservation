package com.stos.concert.exception;

import org.springframework.http.ProblemDetail;

public interface ErrorProcessor {
	void process(final ProblemDetail problemDetail, final Throwable throwable);
}
