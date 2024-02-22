package com.epam.strategy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public abstract class PhoneCameraApp {
	
	private static final Logger LOGGER =LogManager.getLogger(PhoneCameraApp.class);

	public void takeAPhoto() {
		LOGGER.info("Take a Photo.");
	}
	
	public void savePhoto() {
		LOGGER.info("Save Photo.");
	}
	
	public void sharePhoto(String mode) {
		if(mode.equals("email")) {
			LOGGER.info("Share a photo via Email.");

		}else if(mode.equals("text")) {
			LOGGER.info("Share a photo via Text.");

		}
	}
	
	abstract void editPhoto();
}
