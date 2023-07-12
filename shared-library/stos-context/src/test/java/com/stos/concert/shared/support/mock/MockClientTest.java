package com.stos.concert.shared.support.mock;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(classes = {Foo.class, MockFoo.class})
@TestPropertySource(properties = "mock.enabled=true")
public class MockClientTest {

	@Autowired
	Foo foo;

	@Test
	void mockClientTest() {
		foo.print();
		assertThat(foo.getClass()).isEqualTo(MockFoo.class);
	}
}
