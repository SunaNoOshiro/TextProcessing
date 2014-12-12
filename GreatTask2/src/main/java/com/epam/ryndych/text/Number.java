package com.epam.ryndych.text;

import com.epam.ryndych.exception.NotNumberException;



public class Number extends Lexeme {
	public Number(String value) throws NotNumberException {
		
		if(value.matches("[0-9]+[,.]?[0-9]*")==false)
			throw new NotNumberException();
					
	}
	
	@Override
	public String toString() {
		return super.getValue();
	}
}
