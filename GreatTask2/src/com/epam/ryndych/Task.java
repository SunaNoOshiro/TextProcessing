package com.epam.ryndych;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import com.epam.ryndych.text.Sentence;
import com.epam.ryndych.text.Text;

public class Task {

	public  void execute(String path) {
		Main.LOG.info("Start program");

		String inputText = readFromFile(path); // read Text from file

		System.out.println("-------------------------------------------------");
		System.out.println("Вхідний текст :");
		System.out.println("-------------------------------------------------\n");

		System.out.println(inputText);

		System.out.println("\n-------------------------------------------------");
		System.out.println("Текст після заміни табуляцій :");
		System.out.println("-------------------------------------------------\n");

		Text iText = new Text(inputText.toString()); // create object 'Text'

		System.out.println(iText.replaceTabs()); // replace tabs
		System.out.println("\n-------------------------------------------------\n");		
		
		ArrayList<Sentence> iSentence = iText.getSentencesList(); // get list of objects
														         // 'Sentence'

		HashMap<String,Integer> repetitionValues = Text.findRepetition(iSentence);

		//print result
		printRepetitionWords(repetitionValues);		
		
	}	

	private void printRepetitionWords(HashMap<String,Integer> repetitionValues){
		// print all words that are repeated in different sentences
		
		System.out.println("Слова, які зустрічаються в різних реченнях: ");
		int max=0;
		for (String s : repetitionValues.keySet()) {
			if(max<repetitionValues.get(s)) max = repetitionValues.get(s);
			System.out.println("\t" + s + ": " + repetitionValues.get(s));
		}	
		
		//The largest number of sentences that have the same words is:
		System.out.println("Найбільша кількість рядків в яких зустрічаються однакові слова: " + max);
						
	}
	
	private static String readFromFile(String path) {
		Main.LOG.info("Read text from file: '" + path + "'");
		StringBuilder inputText = new StringBuilder();
		File file = new File(path);
		try (InputStream is = new FileInputStream(file);
				InputStreamReader isr = new InputStreamReader(is);) {
			int b = isr.read();
			while (b != -1) {
				inputText.append((char) b);
				// System.out.print((char) b);
				b = isr.read();
			}

		} catch (FileNotFoundException e) {
			Main.LOG.error(e.getMessage());
			e.printStackTrace();
		} catch (IOException e1) {
			Main.LOG.error(e1.getMessage());
			e1.printStackTrace();
		}
		return inputText.toString();
	}
}
