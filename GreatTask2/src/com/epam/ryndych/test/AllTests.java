package com.epam.ryndych.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ NumberTest.class, SentenceTest.class, TaskTest.class,
		TextTest.class })
public class AllTests {

}
