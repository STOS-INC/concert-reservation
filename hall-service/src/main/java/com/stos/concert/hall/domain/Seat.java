package com.stos.concert.hall.domain;

public class Seat {
	private final Type type;
	private final String section;
	private final String row;
	private final int number;

	public Seat(Type type, String section, String row, int number) {
		this.type = type;
		this.section = section;
		this.row = row;
		this.number = number;
	}

	public enum Type {
		STANDARD,
		PREMIUM,
		STANDING,
	}
}
