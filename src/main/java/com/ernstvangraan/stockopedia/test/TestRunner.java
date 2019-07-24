package com.ernstvangraan.stockopedia.test;

import org.jboss.logging.Logger;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
	private static Logger logger = Logger.getLogger(TestRunner.class);

	public static void main(String[] args) {
      Result result = JUnitCore.runClasses(TestSuite.class);

      for (Failure failure : result.getFailures()) {
    	  logger.error(failure.toString());
      }
		
      logger.info(result.wasSuccessful());
   }
}  