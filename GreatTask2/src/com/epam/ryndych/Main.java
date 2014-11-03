package com.epam.ryndych;

import org.apache.log4j.Logger;


public class Main {

	public static final Logger LOG = Logger.getLogger(Main.class);

	public static void main(String[] args) {
		LOG.info("Start program");
		
		new DisplayingOfDirectories().execute();
	}
}
