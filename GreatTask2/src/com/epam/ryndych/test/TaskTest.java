package com.epam.ryndych.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import com.epam.ryndych.Main;
import com.epam.ryndych.Task;

public class TaskTest {

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
		Main.LOG.info("before class TaskTest");
	}

	@Test
	public void readFromFile() {
		String s = new Task().readFromFile("input.tt");
		if (s == null)
			fail();

	}
}
