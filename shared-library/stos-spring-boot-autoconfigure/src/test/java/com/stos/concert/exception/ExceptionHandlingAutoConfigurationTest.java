package com.stos.concert.exception;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.WebApplicationContextRunner;

public class ExceptionHandlingAutoConfigurationTest {
	WebApplicationContextRunner contextRunner;

	@BeforeEach
	void setup() {
		contextRunner = new WebApplicationContextRunner()
			.withConfiguration(AutoConfigurations.of(ExceptionHandlingAutoConfiguration.class));
	}

	@Test
	void shouldBeLoadedExceptionHandling() {
		contextRunner.run(context ->
			assertThat(context)
				.hasSingleBean(DefaultErrorResolver.class)
				.doesNotHaveBean(DebugErrorProcessor.class)
		);
	}

	@Test
	void shouldBeLoadedDebugErrorProcessor() {
		contextRunner
			.withPropertyValues("stos.exception-handling.debug.enabled=true")
			.run(context ->
				assertThat(context)
					.hasSingleBean(DefaultErrorResolver.class)
					.hasSingleBean(DebugErrorProcessor.class)
			);
	}
}
