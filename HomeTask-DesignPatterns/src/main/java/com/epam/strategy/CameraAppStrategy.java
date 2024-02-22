package com.epam.strategy;

public class CameraAppStrategy {

	PhoneCameraApp app;

	CameraAppStrategy(PhoneCameraApp app) {
		this.app = app;
	}

	public void actions(ShareMode mode) {
		app.takePhoto();
		app.editPhoto();
		app.savePhoto();
		mode.share();
	}

}
