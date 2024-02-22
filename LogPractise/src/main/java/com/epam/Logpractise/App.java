package com.epam.Logpractise;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Hello world!
 *
 */

public class App 
{
	private static final Logger LOGGER = LogManager.getLogger(App.class);
    public static void main( String[] args )
    {
        //System.out.println( "Hello World!" );
    	LOGGER.debug("Hello World!!");
    	LOGGER.info("Hello World!!");
    	LOGGER.warn("Hello World!!");
    	LOGGER.error("Hello World!!");
    	LOGGER.fatal("Hello World!!");
    }
}


