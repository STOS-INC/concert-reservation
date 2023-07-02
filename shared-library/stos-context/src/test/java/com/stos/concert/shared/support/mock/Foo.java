package com.stos.concert.shared.support.mock;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Foo {
	void print() {
		log.debug("real method");
	}
}