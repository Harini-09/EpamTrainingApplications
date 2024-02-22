package com.epam.strategy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CameraPlusApp extends PhoneCameraApp{

	private static final Logger LOGGER = LogManager.getLogger(BasicCameraApp.class);
	@Override
	void editPhoto() {
		// new Modifications can done over here.
		LOGGER.info("Edit a Photo.");
	}
	
	@Override
	public void sharePhoto(String mode) {
		if(mode.equals("Instagram")) {
			LOGGER.info("Share a photo via Instagram.");

		}
		super.sharePhoto(mode);
	}

}
