package com.stos.concert.exception;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.core.Ordered;
import org.springframework.http.ProblemDetail;

public class CompositeErrorProcessorTest {
	private CompositeErrorProcessor processor;

	@BeforeEach
	void setup() {
		processor = new CompositeErrorProcessor(List.of(new SecondErrorProcessor(), new FirstErrorProcessor()));
	}

	@DisplayName("Processor들은 Ordered 내림차순으로 처리가 되어야 한다.")
	@Test
	void process_shouldBeProcessFirstErrorProcessor_whenOrderedIsHigher() {
		// given
		final var problemDetail = ProblemDetail.forStatus(200);

		// when
		processor.process(problemDetail, new RuntimeException());

		// then
		assertThat(problemDetail.getTitle()).isEqualTo("first");
	}

	class FirstErrorProcessor implements ErrorProcessor, Ordered {
		@Override
		public void process(ProblemDetail problemDetail, Throwable throwable) {
			problemDetail.setTitle("first");
		}

		@Override
		public int getOrder() {
			return 1;
		}
	}

	private class SecondErrorProcessor implements ErrorProcessor, Ordered {
		@Override
		public void process(ProblemDetail problemDetail, Throwable throwable) {
			problemDetail.setTitle("second");
		}

		@Override
		public int getOrder() {
			return 2;
		}
	}
}
