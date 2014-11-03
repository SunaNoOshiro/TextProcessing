package com.epam.ryndych.text;

abstract public class Lexeme {

	protected String value = "";

	public String getValue() {
		return value;
	}

	public boolean equals(Lexeme obj) {
		return this.value.equals(obj.getValue());
	}
	
	@Override
	public String toString() {
		return value;
	}
}
