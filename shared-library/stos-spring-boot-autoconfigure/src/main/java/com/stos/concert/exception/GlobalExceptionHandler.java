package com.stos.concert.exception;

import static java.util.Objects.*;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	private final ErrorResolver resolver;

	@ExceptionHandler(Throwable.class)
	public ProblemDetail handleUnknownException(final Throwable throwable) {
		return resolver.resolve(throwable);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
		HttpStatusCode statusCode, WebRequest request) {

		final var entity = super.handleExceptionInternal(ex, body, headers, statusCode, request);
		if (nonNull(entity) && entity.getBody() instanceof ProblemDetail problemDetail) {
			final var resolve = resolver.resolve(ex, problemDetail);
			return ResponseEntity.status(entity.getStatusCode())
				.headers(entity.getHeaders())
				.body(resolve);
		}
		return entity;
	}
}
