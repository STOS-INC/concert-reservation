package com.stos.concert.concert.infrastructure.persistence.redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.stos.concert.shared.support.redis.RedisEntity;

@Configuration
public class StosRedisConfiguration {

	// FIXME : shared library에 공통 auto configure로 gogo~
	@Bean
	public RedisTemplate<?, ? extends RedisEntity> redisTemplate(final RedisConnectionFactory connectionFactory) {
		RedisTemplate<?, ? extends RedisEntity> template = new RedisTemplate<>();
		template.setConnectionFactory(connectionFactory);
		template.setEnableTransactionSupport(true);
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
		return template;
	}
}
