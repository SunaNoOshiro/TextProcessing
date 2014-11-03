package com.epam.ryndych.text;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.epam.ryndych.exception.NotNumberException;


@Deprecated
public class Parse {

	String text;
	ArrayList<Lexeme> lexemes = new ArrayList<>();
	ArrayList<Sentence> sentences = new ArrayList<>();

	public Parse() {
	}

	public String replaceTabs(String text) {
		return text = text.replaceAll("[\t ]+", " "); // replace tabs
	}

	public ArrayList<Lexeme> getLexemes()  {
		String regex = "[!?\"?!\\-\\(\\)\\[\\]{}.:;,~^#%*+$@&=|\\\\/_\']|[a-zA-Zà-ÿÀ-ß³¿º²ª¯´¥]+|[0-9]+";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(text);

		while (matcher.find()) {
			if (matcher.group().length() != 0) {

				if (matcher.group().trim().matches("[!?\"?!-.:;,]")) { // if
					// punctuation
					lexemes.add(new Punctuation(matcher.group().trim()));
				} else if (matcher.group().trim()
						.matches("[a-zA-Zà-ÿÀ-ß³¿º²ª¯´¥]+")) { // if
					// word
					lexemes.add(new Word(matcher.group().trim()));
				} else { // if number or something else
					try {
						lexemes.add(new Number(matcher.group().trim()));
					} catch (NotNumberException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}

		return lexemes;
	}

	private int i = 0;

	private StringBuilder createSentences(Lexeme l) {
		StringBuilder sentences = new StringBuilder();

		while (true) {
			if (lexemes.get(i) instanceof Punctuation) {
				// if (lexemes.get(i).getValue().matches("[\"\\(\\['{]")) {
				if (lexemes.get(i).getValue().matches("[\"]")) {
					sentences.append(lexemes.get(i));
					i++;
					break;
				} else {
					sentences.append(lexemes.get(i));
					// sentences.append(" ");
					i++;
				}
			} else {
				sentences.append(" ");
				sentences.append(lexemes.get(i));
				i++;
			}
		}

		return sentences;

	}

	public String createTextWithSentences() {

		StringBuilder text = new StringBuilder();
		while (true) {

			if (i < lexemes.size()) {
				text.append(createSentenceWithLexemes());
				text.append("\n");
				i++;
			} else {
				break;
			}
		}
		return text.toString();
	}

	public String createSentenceWithLexemes() {
		StringBuilder sentence = new StringBuilder();
		if (i < lexemes.size() && lexemes.size() > 0)
			while (true) {
				if (lexemes.get(i) instanceof Punctuation) {
					// if (lexemes.get(i).getValue().matches("[\"\\(\\['{]")) {
					if (lexemes.get(i).getValue().matches("[\"]")) {
						sentence.append(lexemes.get(i));
						i++;
						sentence.append(createSentences(lexemes.get(i))
								.toString());
					} else if (lexemes.get(i).toString().matches("[?!.]")) {
						sentence.append(lexemes.get(i));
						break;
					} else {
						sentence.append(lexemes.get(i));

						i++;
					}
				} else {
					sentence.append(" ");
					sentence.append(lexemes.get(i));

					i++;
				}
			}
		return sentence.toString();
	}
}
