package com.stos.concert.concert.infrastructure.persistence.redis.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import com.stos.concert.concert.infrastructure.TestcontainerConfiguration;
import com.stos.concert.concert.infrastructure.persistence.redis.config.StosRedisConfiguration;
import com.stos.concert.concert.infrastructure.persistence.redis.entity.ScheduledSeatReservationEntityFixture;

@ActiveProfiles("test")
@ContextConfiguration(classes = {TestcontainerConfiguration.class, StosRedisConfiguration.class})
@ImportAutoConfiguration(RedisAutoConfiguration.class)
@SpringBootTest(classes = ScheduledSeatReservationRedisRepository.class)
public class ScheduledSeatReservationRedisRepositoryTest {
	@Autowired
	private ScheduledSeatReservationRedisRepository repository;

	@Test
	void reserve_shouldBeTrue_whenReserveIsSuccess() {
		// given
		final var entity = ScheduledSeatReservationEntityFixture.INSTANCE.entity();

		// when
		final var actual = repository.reserve(entity);

		// then
		assertThat(actual).isTrue();
	}

	@Test
	void reserve_shouldBeFalse_whenEntityIsReserved() {
		// given
		final var entity = ScheduledSeatReservationEntityFixture.INSTANCE.entity();

		// when : reserve가 두번 일어났을 때
		repository.reserve(entity);
		final var actual = repository.reserve(entity);

		// then : false 를 리턴한다
		assertThat(actual).isFalse();
	}

	@Test
	void reserve_shouldBeSuccessOnlyOnce_whenConcurrent() throws InterruptedException {
		// given
		final var count = 10;
		final var entity = ScheduledSeatReservationEntityFixture.INSTANCE.entity();
		final var executorService = Executors.newFixedThreadPool(count);
		final var latch = new CountDownLatch(count);
		final var successCount = new AtomicInteger();

		// when
		IntStream.range(0, count).forEach(i -> {
			executorService.execute(() -> {
				final var result = repository.reserve(entity);
				if (result) {
					successCount.incrementAndGet();
				}
				latch.countDown();
			});
		});
		latch.await();

		// then
		assertThat(successCount).hasValue(1);
	}

}
