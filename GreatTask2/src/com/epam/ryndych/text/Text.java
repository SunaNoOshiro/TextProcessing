package com.epam.ryndych.text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Text {
	private ArrayList<Sentence> sentencesList;
	private String text;

	public Text(String text) {
		this.text = text;
		sentencesList = new ArrayList<Sentence>();
		findSentences();
	}
	
	public String replaceTabs() {
		return text = text.replaceAll("[\t ]+", " "); // replace tabs
	}

	private void findSentences() {
		String sentencesRegex = "[^.!?\\s][^.!?]*(?:[.!?](?!['\"]?\\s|$)[^.!?]*)*[.!?]?['\"]?(?=\\s|$)";
//		Pattern sentencesPattern = Pattern.compile(
//	            "# Match a sentence ending in punctuation or EOS.\n" +
//	                    "[^.!?\\s]    # First char is non-punct, non-ws\n" +
//	                    "[^.!?]*      # Greedily consume up to punctuation.\n" +
//	                    "(?:          # Group for unrolling the loop.\n" +
//	                    "  [.!?]      # (special) inner punctuation ok if\n" +
//	                    "  (?!['\"]?\\s|$)  # not followed by ws or EOS.\n" +
//	                    "  [^.!?]*    # Greedily consume up to punctuation.\n" +
//	                    ")*           # Zero or more (special normal*)\n" +
//	                    "[.!?]?       # Optional ending punctuation.\n" +
//	                    "['\"]?       # Optional closing quote.\n" +
//	                    "(?=\\s|$)", 
//	                    Pattern.MULTILINE | Pattern.COMMENTS);
		Pattern sentencesPattern = Pattern.compile(sentencesRegex);
		Matcher matcher = sentencesPattern.matcher(text);

		
		while (matcher.find()) {
			if (matcher.group().length() != 0) {
				sentencesList.add(new Sentence(matcher.group().trim()));
				// System.out.println("\t"+matcher.group().trim());
			}
		}
		System.out.println();
	}

	public ArrayList<Sentence> getSentencesList() {
		return sentencesList;
	}

	// return hashmap that contain words and their numbers of repetitions
	public static HashMap<String, Integer> findRepetition(ArrayList<Sentence> listOfSentences) {
		HashMap<String, Integer> repetitionValues = new HashMap<String, Integer>();
														// key - a word that is repeated;
													   // value - the number of repetitions
													  // in different sentence

		HashMap<String, Integer> map = new HashMap<String, Integer>();
											// key - a word that is repeated;
										   // value - the sentence number in which first met

		int indexOfSentence = 0; // index of current sentence

		for (Sentence s : listOfSentences) {

			for (Lexeme l : s.getListOfLexemes()) {
				if (l instanceof Word) { // if lexeme is a word	
					
					if (map.get(l.toString()) == null) {						
						// If the word is met for the first time
						
						map.put(l.toString(), indexOfSentence);
					} else {
						// If the word meets not for the first time
						
						if (map.get(l.toString()) != indexOfSentence) {
							// System.out.println(l.toString());

							if (repetitionValues.get(l.toString()) == null)
								repetitionValues.put(l.toString(), 2);
							else {
								// change the number of repetitions
								int w = repetitionValues.get(l.toString());
								repetitionValues.replace(l.toString(), w + 1);
								map.replace(l.toString(),indexOfSentence );
							}
						}
					}
				}
			}
			indexOfSentence++;
		}
		return repetitionValues;
	}

}