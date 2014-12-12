package com.epam.ryndych.test;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import com.epam.ryndych.Main;
import com.epam.ryndych.text.Sentence;
import com.epam.ryndych.text.Text;

public class TextTest {

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
	public void testReplaceTabs() {
		assertArrayEquals(" hello world".toCharArray(), new Text(
				"\t\t\t hello     \tworld").replaceTabs().toCharArray());
		assertNotEquals("\t\t\t hello     \tworld", new Text(
				"\t\t\t hello     \tworld").replaceTabs());
	}

	@Test
	public void testFindSentences() throws NoSuchMethodException,
			SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			NoSuchFieldException {

		Text text = new Text("Hello.My beautiful world Hello!");
		ArrayList<Sentence> listOfSentences;

		Method findSentences = Text.class.getDeclaredMethod("findSentences",
				null);
		findSentences.setAccessible(true);

		findSentences.invoke(text);
		Field sentencesList = Text.class.getDeclaredField("sentencesList");
		sentencesList.setAccessible(true);
		ArrayList<Sentence> res = (ArrayList<Sentence>) sentencesList.get(text);
		if (res.size() == 0) {
			fail();
		}
		
		HashMap<String, Integer>  map =text.findRepetition(res);
		if(map.size()==0){
			fail();
		}

	}

}
