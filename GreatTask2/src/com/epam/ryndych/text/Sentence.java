package com.epam.ryndych.text;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.epam.ryndych.exception.NotNumberException;


public class Sentence {
	private ArrayList<Lexeme> listOfLexemes = new ArrayList<>();
	private String sentence;

	public Sentence(String sentence) {
		this.sentence = sentence;
		findLexemes(); // split the sentence on tokens
	}

	public Sentence(ArrayList<Lexeme> listOfLexemes) {
		this.listOfLexemes = listOfLexemes;
		sentenceBuilding(); // build the sentence with the tokens
	}

	private void findLexemes() {
		String lexemeRegex = "[!?\"?!\\-\\(\\)\\[\\]{}.:;,~^#%*+$@&=|\\\\/_\']|[a-zA-Zà-ÿÀ-ß³¿º²ª¯´¥]+|[0-9]+";
		Pattern pattern = Pattern.compile(lexemeRegex);
		Matcher matcher = pattern.matcher(sentence);

		while (matcher.find()) {
			if (matcher.group().length() != 0) {

				if (matcher.group().trim().matches("[!?\"?!-.:;,]")) { // if
																		// punctuation
					listOfLexemes.add(new Punctuation(matcher.group().trim()));
				} else if (matcher.group().trim()
						.matches("[a-zA-Zà-ÿÀ-ß³¿º²ª¯´¥]+")) { // if
																// word
					listOfLexemes.add(new Word(matcher.group().trim()));
				} else if (matcher.group().trim().matches("\\d*[.,]?\\d+")) { // if
																				// number
					
						try {
							listOfLexemes.add(new Number(matcher.group().trim()));
						} catch (NotNumberException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
				} else { // something else
					listOfLexemes
							.add(new UnknownSymbol(matcher.group().trim()));
				}
			}
		}
	}

	private void sentenceBuilding() { // formation of sentence with tokens
		StringBuilder sentenceBuilder = new StringBuilder();

		for (Lexeme l : listOfLexemes) {
			if (l instanceof Word || l instanceof Number) { // if lexeme contain
															// Word or Number
				sentenceBuilder.append(" ");
				sentenceBuilder.append(l.toString());
			} else if (l instanceof Punctuation) { // if lexeme contain
													// Punctuation
				sentenceBuilder.append(l.toString());
			}
		}

		sentence = sentenceBuilder.toString();
	}

	public boolean hasTheSameWords() {
		boolean hasTheSame = false;
		Lexeme first;
		Lexeme second;
		for (int i = 0; i < listOfLexemes.size(); i++) {
			first = listOfLexemes.get(i);
			if (first instanceof Word)
				for (int j = 0; j < listOfLexemes.size(); j++) {
					second = listOfLexemes.get(j);
					if (second instanceof Word) {

						if (first.equals(second) && first != second) {
							// System.out.println("first="+ first+
							// "; second="+second);
							hasTheSame = true;
						}
					}
				}
		}
		return hasTheSame;
	}

	public ArrayList<Lexeme> getListOfLexemes() {
		return listOfLexemes;
	}

	@Override
	public String toString() {
		return sentence;
	}

}
