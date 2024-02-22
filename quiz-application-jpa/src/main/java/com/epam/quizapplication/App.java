package com.epam.quizapplication;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;


/**
 * Hello world!
 *
 */
public class App 
{
	private static final Logger LOGGER = (Logger) LogManager.getLogger(App.class);
    public static void main( String[] args )
    {
        LOGGER.error( "Hello World!" );
    }
}
