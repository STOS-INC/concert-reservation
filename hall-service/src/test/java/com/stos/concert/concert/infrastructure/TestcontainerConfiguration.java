package com.stos.concert.concert.infrastructure;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MySQLContainer;

@TestConfiguration(proxyBeanMethods = false)
public class TestcontainerConfiguration {

	@Bean
	@ServiceConnection
	public MySQLContainer<?> mysqlContainer() {
		return new MySQLContainer<>("mysql:latest");
	}

	@Bean
	@ServiceConnection(name = "redis")
	public GenericContainer<?> redisContainer() {
		return new GenericContainer<>("redis:latest").withExposedPorts(6379).withReuse(true);
	}
}