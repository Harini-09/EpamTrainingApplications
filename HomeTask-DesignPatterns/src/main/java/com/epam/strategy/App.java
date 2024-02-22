package com.epam.strategy;

public class App {

	public static void main(String[] args) {
		
		PhoneCameraApp app1 = new BasicCameraApp();
		CameraAppStrategy strategy1 = new CameraAppStrategy(app1);
		ShareMode emailmode = new EmailMode();
		strategy1.actions(emailmode);
		
		PhoneCameraApp app2 = new CameraPlusApp();
		CameraAppStrategy strategy2 = new CameraAppStrategy(app2);
		ShareMode textmode = new TextMode();
		strategy2.actions(textmode);
		
	}

}
