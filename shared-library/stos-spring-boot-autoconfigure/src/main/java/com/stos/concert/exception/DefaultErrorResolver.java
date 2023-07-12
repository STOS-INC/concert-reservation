package com.stos.concert.exception;

import java.util.List;

import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponse;

import com.stos.concert.shared.exception.AbstractStosException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultErrorResolver implements ErrorResolver {
	private final ErrorProcessor processor;

	public DefaultErrorResolver(final List<ErrorProcessor> processors) {
		this.processor = new CompositeErrorProcessor(processors);
	}

	@Override
	public ProblemDetail resolve(final Throwable throwable) {
		ProblemDetail problemDetail;
		if (throwable instanceof ErrorResponse exception) {
			problemDetail = exception.getBody();
		} else if (throwable instanceof AbstractStosException exception) {
			problemDetail = ProblemDetail.forStatus(exception.status());
			problemDetail.setType(exception.type());
			problemDetail.setTitle(exception.reason());
		} else {
			problemDetail = ProblemDetail.forStatus(500);
			problemDetail.setTitle(throwable.getMessage());
		}
		return resolve(throwable, problemDetail);
	}

	@Override
	public ProblemDetail resolve(final Throwable throwable, final ProblemDetail problemDetail) {
		processor.process(problemDetail, throwable);
		log.debug("error : {}", problemDetail, throwable);
		return problemDetail;
	}
}
