package com.epam.builder;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Main {
	private static final Logger LOGGER = LogManager.getLogger(Main.class);
	public static void main(String[] args) {
		Bicycle bicycle1 = Bicycle.builder()
								 .gears(false)
								 .doubleSeats(true)
								 .doubleStands(false)
								 .carrier(true)
								 .company(true)
								 .build();
		LOGGER.info(bicycle1);
		
	}

}
