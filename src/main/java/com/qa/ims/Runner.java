package com.qa.ims;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Runner {

	public static final Logger LOGGER = LogManager.getLogger(Runner.class);

	public static void main(String[] args) {
		Ims ims = new Ims();
		ims.ImsSystem();
		LOGGER.info("Goodbye");
	}

}
