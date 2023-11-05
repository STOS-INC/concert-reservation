package com.stos.concert.shared.support.redis;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class RedisRepositoryTest {

	@Test
	public void typeKey() {
		// given
		final var repository = new FooRepository();

		// when
		final var actual = repository.typeKey("foo");

		// then
		assertThat(actual).isEqualTo("foo");
	}

	@Test
	public void idKey() {
		// given
		final var repository = new FooRepository();

		// when
		final var actualFromNoKey = repository.idKey("foo");
		final var actualFromSingleKey = repository.idKey("foo", "bar");
		final var actualFromMultipleKey = repository.idKey("foo", "bar", "baz");

		// then
		assertThat(actualFromNoKey).isEqualTo("{foo}:");
		assertThat(actualFromSingleKey).isEqualTo("{foo}:bar");
		assertThat(actualFromMultipleKey).isEqualTo("{foo}:bar#baz");
	}

	class FooRepository implements RedisRepository {
		@Override
		public RedisKeyType type() {
			return RedisKeyType.DEFAULT;
		}

	}
}
