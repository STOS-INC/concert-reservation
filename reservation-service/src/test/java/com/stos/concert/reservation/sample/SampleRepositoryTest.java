package com.stos.concert.reservation.sample;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

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
