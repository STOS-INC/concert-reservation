package com.stos.concert.reservation.sample;

import static org.assertj.core.api.Assertions.assertThat;

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

    assertThat("TEST 1").isEqualTo(res.getName());
  }
}
