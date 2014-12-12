package com.epam.ryndych.test;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import com.epam.ryndych.Main;
import com.epam.ryndych.text.*;

public class SentenceTest {

	@Rule
	public TestWatcher testWatcher = new TestWatcher() {
		@Override
		protected void failed(Throwable e, Description description) {
			Main.LOG.error(e.getMessage());
			super.failed(e, description);
		}

		protected void succeeded(Description description) {
			Main.LOG.info(description);
			super.succeeded(description);
		};
	};

	@Before
	public void before() {
		Main.LOG.info("before class TextTest");
	}

	@Test
	public void testConstructor() throws NoSuchFieldException,
			SecurityException, IllegalArgumentException, IllegalAccessException {

		Sentence sentence = new Sentence("hello world");
		Field listOfLexemes = Sentence.class.getDeclaredField("listOfLexemes");
		listOfLexemes.setAccessible(true);
		@SuppressWarnings("unchecked")
		ArrayList<Lexeme> arrayList = (ArrayList<Lexeme>) listOfLexemes.get(sentence);
		if (arrayList.size() == 0) {
			fail();
		}
	}
	
	@Test
	public void testHasTheSameWords(){
		Sentence sentence = new Sentence("hello world");
		assertEquals(false,sentence.hasTheSameWords());
		
		sentence = new Sentence("hello world world");
		assertEquals(true,sentence.hasTheSameWords());
	}
}
