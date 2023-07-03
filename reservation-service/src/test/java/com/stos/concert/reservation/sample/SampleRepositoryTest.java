package com.stos.concert.reservation.sample;

import static org.testcontainers.shaded.org.awaitility.Awaitility.await;

import com.stos.concert.reservation.TestReservationServiceApplication;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.Duration;
import javax.sql.DataSource;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SampleRepositoryTest {

  @Autowired
  private SampleRepository sampleRepository;

  @Test
  void insertNewSample() {
    SampleEntity sampleEntity = SampleEntity.builder().name("TEST 1").description("TEST 1").build();
    sampleRepository.save(sampleEntity);

    SampleEntity res = sampleRepository.findById(1L).orElseThrow();

    Assertions.assertEquals("TEST 1", res.getName());
  }
}
