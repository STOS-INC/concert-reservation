package com.stos.concert.exception;

import org.springframework.http.ProblemDetail;

public interface ErrorResolver {
	ProblemDetail resolve(final Throwable throwable);

	ProblemDetail resolve(final Throwable throwable, final ProblemDetail problemDetail);
}
