package com.stos.concert.shared.support.mock;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@MockClient
public class MockFoo extends Foo{
	@Override
	void print() {
		log.debug("fake method");
	}
}