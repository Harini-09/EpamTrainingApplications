package com.epam.strategy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BasicCameraApp extends PhoneCameraApp{

	private static final Logger LOGGER = LogManager.getLogger(BasicCameraApp.class);
	
	PhoneCameraApp cameraApp;
	
	public BasicCameraApp(PhoneCameraApp cameraApp) {
		this.cameraApp = cameraApp;
	}
	@Override
	void editPhoto() {
		// new Modifications can done over here.
		LOGGER.info("Custom Editing of a Photo.");
	}
	
	@Override
	public void sharePhoto(String mode) {
		if(mode.equals("Instagram")) {
			LOGGER.info("Share a photo via Instagram.");

		}
		super.sharePhoto(mode);
	}

}
