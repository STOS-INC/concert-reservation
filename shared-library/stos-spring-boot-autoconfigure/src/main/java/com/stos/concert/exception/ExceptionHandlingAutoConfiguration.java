package com.stos.concert.exception;

import java.util.List;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;

@AutoConfiguration(before = {ErrorMvcAutoConfiguration.class})
@ConditionalOnWebApplication
public class ExceptionHandlingAutoConfiguration {

	@ConditionalOnMissingBean(ErrorResolver.class)
	@Bean
	public ErrorResolver defaultErrorResolver(final List<ErrorProcessor> processors) {
		return new DefaultErrorResolver(processors);
	}

	@ConditionalOnProperty(value = "stos.exception-handling.debug.enabled", havingValue = "true")
	@Bean
	public DebugErrorProcessor debugErrorProcessor() {
		return new DebugErrorProcessor();
	}

}
