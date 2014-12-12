package com.epam.ryndych.test;

import com.epam.ryndych.Main;
import com.epam.ryndych.exception.NotNumberException;
import com.epam.ryndych.text.Number;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class NumberTest {

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
		Main.LOG.info("before class NumberTest");
	}

	@Test(expected = NotNumberException.class)
	public void testConstructor1() throws NotNumberException {
		new Number("45,p6");
	}

	@Test(expected = NotNumberException.class)
	public void testConstructor2() throws NotNumberException {
		new Number("s454,5");
	}

	@Test(expected = NotNumberException.class)
	public void testConstructor3() throws NotNumberException {
		new Number("45,96sdfsdf");
	}
	
	@Test(expected = NotNumberException.class)
	public void testConstructor4() throws NotNumberException {
		new Number("45`598");
	}
}
