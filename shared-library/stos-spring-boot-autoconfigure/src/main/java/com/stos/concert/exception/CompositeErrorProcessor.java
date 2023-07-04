package com.stos.concert.exception;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.http.ProblemDetail;

public class CompositeErrorProcessor implements ErrorProcessor {
	private final List<ErrorProcessor> processors;

	public CompositeErrorProcessor(List<ErrorProcessor> processors) {
		this.processors = processors.stream()
			.filter((Predicate.not(this::equals))) // composite 은 제외
			.sorted(AnnotationAwareOrderComparator.INSTANCE.reversed()) // 우선순위가 높을수록 override 되도록 나중 적용
			.toList();
	}

	@Override
	public void process(ProblemDetail problemDetail, Throwable throwable) {
		processors.forEach(processor -> processor.process(problemDetail, throwable));
	}
}
