package com.stos.concert.shared.exception;

import static java.util.Objects.*;

import java.io.Serial;
import java.net.URI;

import org.springframework.util.ClassUtils;

import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public abstract class AbstractStosException extends RuntimeException{

	@Serial private static final long serialVersionUID = -5205832509585611587L;
	public static final String SCHEME = "exception";
	private final int status;
	private final String reason;

	protected AbstractStosException(final int status) {
		this(status, null, null);
	}

	protected AbstractStosException(final int status, final Throwable cause) {
		this(status, null, cause);
	}
	protected AbstractStosException(final int status, final String reason) {
		this(status, reason, null);
	}

	protected AbstractStosException(final int status, final String reason, final Throwable cause) {
		super(null, cause);
		this.status = status;
		this.reason = reason;
	}

	@Override
	public String getMessage() {
		final var builder = new StringBuilder();
		builder.append(this.status);
		if (nonNull(this.reason)) {
			builder.append("[").append(this.reason).append("]");
		}
		if (nonNull(getCause())) {
			builder.append("\n").append("[exception : ")
				.append(getCause()).append("]");
		}
		return builder.toString();
	}

	public URI type() {
		return URI.create(SCHEME + ":" + ClassUtils.getShortName(getClass()));
	}



}
