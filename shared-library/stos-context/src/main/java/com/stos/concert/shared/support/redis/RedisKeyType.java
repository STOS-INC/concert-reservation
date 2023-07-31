package com.stos.concert.shared.support.redis;

import java.util.Arrays;

public enum RedisKeyType {
	SEAT("seat"),
	DEFAULT("");

	private final String typeName;

	RedisKeyType(String typeName) {
		this.typeName = typeName;
	}

	public static RedisKeyType typeOf(String type) {
		return Arrays.stream(RedisKeyType.values())
			.filter(value -> type.equals(value.typeName))
			.findAny()
			.orElseGet(() -> DEFAULT);
	}

	public String hashTag() {
		return String.format("{%s}", this.typeName);
	}
}
